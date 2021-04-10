package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.core.foundation.SearchProvider;
import com.cms.service.dto.CmsContentDto;

import java.util.List;

public interface CmsContentService extends BaseService<CmsContentDto,Integer> {

    /**
     * 内容后置操作
     * @param cmsContentDto
     */
    void afterOperationStatus(CmsContentDto cmsContentDto);

    /**
     * 根据指令获取内容列表
     * @param inner
     * @return
     */
    List<CmsContentDto> getListByDirective(SearchProvider.Inner inner);
}
