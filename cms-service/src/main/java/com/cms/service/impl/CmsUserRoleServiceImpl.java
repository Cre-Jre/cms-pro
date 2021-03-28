package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.mapper.CmsUserRoleMapper;
import com.cms.service.api.CmsUserRoleService;
import com.cms.service.converter.CmsUserRoleConverter;
import com.cms.service.dto.CmsUserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsUserRoleServiceImpl implements CmsUserRoleService {

    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;

    @Override
    public void save(CmsUserRoleDto dto) {
        cmsUserRoleMapper.save(CmsUserRoleConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {
        cmsUserRoleMapper.deleteById(id);
    }

    @Override
    public void update(CmsUserRoleDto dto) {

    }

    @Override
    public CmsUserRoleDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsUserRoleDto> getPage(CmsUserRoleDto dto) {
        return null;
    }

    @Override
    public List<String> selectPermissionsByUserId(Integer userId) {
        return cmsUserRoleMapper.selectPermissionsByUserId(userId);
    }
}
