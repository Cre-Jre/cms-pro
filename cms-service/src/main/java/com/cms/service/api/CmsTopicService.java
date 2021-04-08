package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsTopicDto;

import java.util.List;

public interface CmsTopicService extends BaseService<CmsTopicDto,Integer> {
    /**
     * 根据页面参数查询
     * @param count        条数
     * @return              list
     */
    List<CmsTopicDto> getListByTag(int count);

    /**
     * 获取列表
     * @param cmsTopicDto
     * @return
     */
    List<CmsTopicDto> getList(CmsTopicDto cmsTopicDto);

}
