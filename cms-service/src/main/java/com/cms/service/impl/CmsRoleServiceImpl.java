package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
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
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        cmsRolePermissionMapper.deleteByRoleId(id);
        cmsRoleMapper.deleteById(id);
    }

    @Override
    public void update(CmsRoleDto dto) {
        cmsRoleMapper.update(CmsRoleConverter.CONVERTER.dtoToEntity(dto));
        cmsRolePermissionMapper.deleteByRoleId(dto.getId());
        if(!dto.getAll()){
            List<Integer> permission = dto.getPermission();
            if(CollectionUtils.isNotEmpty(permission)){
                cmsRolePermissionMapper.batchInsert(permission,dto.getId());
            }
        }
    }

    @Override
    public CmsRoleDto getById(Integer id) {
        return CmsRoleConverter.CONVERTER.entityToDto(cmsRoleMapper.selectById(id));
    }

    @Override
    public Page<CmsRoleDto> getPage(CmsRoleDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsRoleConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsRoleEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsRoleMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsRoleConverter.CONVERTER.entityToDto(page.getResult()));
    }

    @Override
    public List<CmsRoleDto> getList() {
        SearchProvider of = SearchProvider.of(CmsRoleConverter.CONVERTER.dtoToEntity(new CmsRoleDto()));
        return CmsRoleConverter.CONVERTER.entityToDto(cmsRoleMapper.selectBySearchProvider(of));
    }
}
