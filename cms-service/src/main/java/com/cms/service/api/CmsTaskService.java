package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsTaskDto;

import java.util.List;

public interface CmsTaskService extends BaseService<CmsTaskDto,Integer> {

    /**
     * ִ������
     * @param cmsTaskDto    dto
     */
    void startTask(CmsTaskDto cmsTaskDto);

    /**
     * ��ȡ�б�
     * @return      list
     */
    List<CmsTaskDto> getList();
}
