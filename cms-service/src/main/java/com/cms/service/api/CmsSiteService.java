package com.cms.service.api;


import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsSiteDto;

public interface CmsSiteService extends BaseService<CmsSiteDto,Integer> {
    /**
     * 获取当前站点信息
     * @return
     */
    CmsSiteDto get();
}
