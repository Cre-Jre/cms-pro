package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsContentDto;

public interface CmsContentService extends BaseService<CmsContentDto,Integer> {

    /**
     * ÄÚÈÝºóÖÃ²Ù×÷
     * @param cmsContentDto
     */
    void afterOperationStatus(CmsContentDto cmsContentDto);

}
