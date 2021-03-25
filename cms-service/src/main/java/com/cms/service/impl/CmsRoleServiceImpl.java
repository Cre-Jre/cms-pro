package com.cms.service.impl;

import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.mapper.CmsRoleMapper;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsRoleService;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.dto.CmsRoleDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsRoleServiceImpl implements CmsRoleService {

    @Autowired
    private CmsRoleMapper cmsRoleMapper;
    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    public void save(CmsRoleDto dto) {
        CmsRoleEntity cmsRoleEntity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        cmsRoleMapper.save(cmsRoleEntity);
        if(dto.getAll()){
            List<Integer> permission = dto.getPermission();
            if(CollectionUtils.isNotEmpty(permission)){
                cmsRolePermissionMapper.batchInsert(permission,cmsRoleEntity.getId());
            }
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsRoleDto dto) {

    }

    @Override
    public CmsRoleDto getById(Integer id) {
        return null;
    }
}
