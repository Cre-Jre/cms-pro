package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsChannelDto;

import java.util.List;

public interface CmsChannelService extends BaseService<CmsChannelDto,Integer> {

    /**
     * 获取列表数据
     * @param cmsChannelDto
     * @return
     */
    List<CmsChannelDto> getList(CmsChannelDto cmsChannelDto);

}
