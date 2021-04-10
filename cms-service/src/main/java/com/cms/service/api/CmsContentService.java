package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.core.foundation.SearchProvider;
import com.cms.service.dto.CmsContentDto;

import java.util.List;

public interface CmsContentService extends BaseService<CmsContentDto,Integer> {

    /**
     * ���ݺ��ò���
     * @param cmsContentDto
     */
    void afterOperationStatus(CmsContentDto cmsContentDto);

    /**
     * ����ָ���ȡ�����б�
     * @param inner
     * @return
     */
    List<CmsContentDto> getListByDirective(SearchProvider.Inner inner);
}
