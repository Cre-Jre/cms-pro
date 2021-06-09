package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.contex.utils.UtilsString;
import com.cms.core.exception.BusinessException;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.enums.TaskExecutionCycleUnitEnum;
import com.cms.dao.enums.TaskExecutionTypeEnum;
import com.cms.dao.enums.TaskStaticTypeEnum;
import com.cms.dao.mapper.CmsTaskMapper;
import com.cms.service.api.CmsTaskService;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.converter.CmsTaskConverter;
import com.cms.service.dto.CmsTaskDto;
import com.cms.service.strategy.*;
import com.cms.service.task.job.IndexStaticJob;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class CmsTaskServiceImpl implements CmsTaskService {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private CmsTaskMapper cmsTaskMapper;

    /**
     * 策略map
     * @param dto
     */
    private static final Map<TaskExecutionCycleUnitEnum, TaskCronExpressionStrategy> TASK_CRON_EXPRESSION = new HashMap<TaskExecutionCycleUnitEnum, TaskCronExpressionStrategy>(){{
        put(TaskExecutionCycleUnitEnum.MIN, new TaskMinuteCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.HOUR, new TaskHourCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.DAY, new TaskDayCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.WEEK, new TaskWeekCronExpressionStrategy());
        put(TaskExecutionCycleUnitEnum.MONTH, new TaskMonthCronExpressionStrategy());
    }};

    /**
     * 执行具体任务的job Map
     * @param dto
     */
    private static final Map<TaskStaticTypeEnum,Class<? extends QuartzJobBean>> TASK_JOB_CLASS_MAP = new HashMap<TaskStaticTypeEnum,Class<? extends QuartzJobBean>>(){{
        put(TaskStaticTypeEnum.INDEX, IndexStaticJob.class);
    }};

    @Override
    public void save(CmsTaskDto dto) {
        dto.setCode(UtilsString.uuid());
        CmsTaskEntity cmsTaskEntity = CmsTaskConverter.CONVERTER.dtoToEntity(dto);
        cmsTaskMapper.save(cmsTaskEntity);
        if(BooleanUtils.isTrue(dto.getEnable())){
            startTask(dto);
        }
    }

    public void startTask(CmsTaskDto cmsTaskDto){
        TaskExecutionTypeEnum taskExecutionType = cmsTaskDto.getTaskExecutionType();
        String cronExpression = Objects.equals(taskExecutionType, TaskExecutionTypeEnum.EXECUTION_MODE) ? cmsTaskDto.getCronExpression() : TASK_CRON_EXPRESSION.get(cmsTaskDto.getIntervalUnit()).buildCronExpress(cmsTaskDto);
        log.info("cronExpression表达式=[{}]",cronExpression);
        if(StringUtils.contains(cronExpression,"null")){
            return;
        }
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setName(cmsTaskDto.getCode());
        jobDetailFactoryBean.setJobClass(TASK_JOB_CLASS_MAP.get(cmsTaskDto.getType()));
        jobDetailFactoryBean.afterPropertiesSet();

        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setCronExpression(cronExpression);
        cronTriggerFactoryBean.setName(cmsTaskDto.getName()+cmsTaskDto.getCode());
        try {
            cronTriggerFactoryBean.afterPropertiesSet();
            scheduler.scheduleJob(jobDetailFactoryBean.getObject(),cronTriggerFactoryBean.getObject());
        } catch (Exception e) {
            log.error("执行定时任务失败,message=[{}]",e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<CmsTaskDto> getList() {
        return CmsTaskConverter.CONVERTER.entityToDto(cmsTaskMapper.selectAll());
    }

    @Override
    public void deleteById(Integer id) {
        CmsTaskDto cmsTaskDto = getById(id);
        if(Objects.isNull(cmsTaskDto)){
            throw new BusinessException("当前任务不存在");
        }
        if(!deleteJob(cmsTaskDto.getCode()))         {
            throw new BusinessException("当前任务无法删除,请联系管理员");
        }
        cmsTaskMapper.deleteById(id);
    }

    @Override
    public void update(CmsTaskDto dto) {
        CmsTaskDto cmsTaskDto = getById(dto.getId());
        if(Objects.isNull(cmsTaskDto)){
            throw new BusinessException("当前任务不存在");
        }
        deleteJob(cmsTaskDto.getCode());
        dto.setCode(UtilsString.uuid());
        cmsTaskMapper.update(CmsTaskConverter.CONVERTER.dtoToEntity(dto));
        startTask(dto);
    }

    /**
     * 删除某个指定名称的job
     * @param jobName           job名称
     */
    public boolean deleteJob(String jobName){
        boolean result = false;
        try {
            result = scheduler.deleteJob(JobKey.jobKey(jobName));
            log.info("结束[{}]任务,结果=[{}]",jobName,result);
        } catch (SchedulerException e) {
            log.error("删除定时任务失败=[{}]",e.getMessage());
        }
        return result;
    }

    @Override
    public CmsTaskDto getById(Integer id) {
        return CmsTaskConverter.CONVERTER.entityToDto(cmsTaskMapper.selectById(id));
    }

    @Override
    public Page<CmsTaskDto> getPage(CmsTaskDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsTaskConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsTaskEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsTaskMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsTaskConverter.CONVERTER.entityToDto(page.getResult()));
    }
}
