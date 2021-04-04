package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsTaskDto;

import java.util.List;

public interface CmsTaskService extends BaseService<CmsTaskDto,Integer> {

    /**
     * 执行任务
     * @param cmsTaskDto    dto
     */
    void startTask(CmsTaskDto cmsTaskDto);

    /**
     * 获取列表
     * @return      list
     */
    List<CmsTaskDto> getList();
}
