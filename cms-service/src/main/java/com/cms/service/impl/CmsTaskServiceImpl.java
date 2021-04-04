package com.cms.service.impl;

import com.cms.contex.utils.UtilsString;
import com.cms.core.foundation.Page;
import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.enums.TaskExecutionCycleUnitEnum;
import com.cms.dao.enums.TaskStaticTypeEnum;
import com.cms.dao.mapper.CmsTaskMapper;
import com.cms.service.api.CmsTaskService;
import com.cms.service.converter.CmsTaskConverter;
import com.cms.service.dto.CmsTaskDto;
import com.cms.service.strategy.*;
import com.cms.service.task.job.IndexStaticJob;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CmsTaskServiceImpl implements CmsTaskService {

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
    private static final Map<TaskStaticTypeEnum,Class<? extends QuartzJobBean>> task_job_class_map = new HashMap<TaskStaticTypeEnum,Class<? extends QuartzJobBean>>(){{
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

    }


    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsTaskDto dto) {

    }

    @Override
    public CmsTaskDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsTaskDto> getPage(CmsTaskDto dto) {
        return null;
    }
}
