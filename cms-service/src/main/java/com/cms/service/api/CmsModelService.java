package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsModelDto;

import java.util.List;

public interface CmsModelService extends BaseService<CmsModelDto,Integer> {

    /**
     * ��ȡ�б�����
     * @param cmsModelDto
     * @return
     */
    List<CmsModelDto> list(CmsModelDto cmsModelDto);

}
