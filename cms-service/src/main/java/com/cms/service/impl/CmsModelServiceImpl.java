package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.service.api.CmsModelService;
import com.cms.service.dto.CmsModelDto;
import org.springframework.stereotype.Service;

@Service
public class CmsModelServiceImpl implements CmsModelService {
    @Override
    public void save(CmsModelDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsModelDto dto) {

    }

    @Override
    public CmsModelDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsModelDto> getPage(CmsModelDto dto) {
        return null;
    }
}
