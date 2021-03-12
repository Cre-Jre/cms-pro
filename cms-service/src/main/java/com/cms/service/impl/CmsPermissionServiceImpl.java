package com.cms.service.impl;

import com.cms.dao.mapper.CmsPermissionMapper;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.converter.CmsPermissionConverter;
import com.cms.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {

    @Autowired
    private CmsPermissionMapper cmsPermissionMapper;

    @Override
    public void save(CmsPermissionDto dto) {
        cmsPermissionMapper.save(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void update(CmsPermissionDto dto) {

    }

    @Override
    public CmsPermissionDto getById(Integer id) {
        return null;
    }
}
