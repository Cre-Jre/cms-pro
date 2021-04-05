package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.mapper.CmsModelMapper;
import com.cms.service.api.CmsModelService;
import com.cms.service.converter.CmsModelConverter;
import com.cms.service.dto.CmsModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsModelServiceImpl implements CmsModelService {

    @Autowired
    private CmsModelMapper cmsModelMapper;

    @Override
    public void save(CmsModelDto dto) {
        cmsModelMapper.save(CmsModelConverter.CONVERTER.dtoToEntity(dto));
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
