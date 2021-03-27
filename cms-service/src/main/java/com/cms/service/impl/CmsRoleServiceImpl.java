package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.mapper.CmsRoleMapper;
import com.cms.dao.mapper.CmsRolePermissionMapper;
import com.cms.service.api.CmsRoleService;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.dto.CmsRoleDto;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CmsRoleServiceImpl implements CmsRoleService {

    @Autowired
    private CmsRoleMapper cmsRoleMapper;
    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CmsRoleDto dto) {
        CmsRoleEntity cmsRoleEntity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        cmsRoleMapper.save(cmsRoleEntity);
        if(!dto.getAll()){
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

    @Override
    public Page<CmsRoleDto> getPage(CmsRoleDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        com.github.pagehelper.Page<CmsRoleEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsRoleMapper.selectByPage(CmsRoleConverter.CONVERTER.dtoToEntity(dto)));
        return new Page<>(page.getTotal(),CmsRoleConverter.CONVERTER.entityToDto(page.getResult()));
    }
}
