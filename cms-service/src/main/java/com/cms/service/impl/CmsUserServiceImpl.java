package com.cms.service.impl;

import com.cms.contex.utils.UtilsProperties;
import com.cms.contex.utils.UtilsShiro;
import com.cms.core.foundation.Page;
import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.mapper.CmsUserMapper;
import com.cms.service.api.CmsUserRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.converter.CmsUserConverter;
import com.cms.service.dto.CmsUserDto;
import com.cms.service.dto.CmsUserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CmsUserServiceImpl implements CmsUserService {

    @Autowired
    private CmsUserMapper cmsUserMapper;
    @Autowired
    private UtilsProperties utilsProperties;
    @Autowired
    private CmsUserRoleService cmsUserRoleService;

    @Override
    public CmsUserDto selectByUsername(String username) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getByUsername(username));
    }

    @Override
    public CmsUserDto selectByEmail(String email) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getByEmail(email));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CmsUserDto dto) {
        String salt = UtilsShiro.generateSalt();
        dto.setSalt(salt);
        dto.setAdmin(true);
        String password = dto.getPassword();
        dto.setPassword(UtilsShiro.sha256(password,salt,Integer.parseInt(utilsProperties.getPropertiesValue("shiro.hash.iterations"))));
        dto.setRegisterTime(LocalDateTime.now());
        CmsUserEntity cmsUserEntity = CmsUserConverter.CONVERTER.dtoToEntity(dto);
        cmsUserMapper.save(cmsUserEntity);
        Integer roleId = dto.getRoleId();
        if(Objects.nonNull(roleId)){
            CmsUserRoleDto cmsUserRoleDto = new CmsUserRoleDto();
            cmsUserRoleDto.setRoleId(roleId);
            cmsUserRoleDto.setUserId(cmsUserEntity.getId());
            cmsUserRoleService.save(cmsUserRoleDto);
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsUserDto dto) {
        cmsUserMapper.update(CmsUserConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsUserDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsUserDto> getPage(CmsUserDto dto) {
        return null;
    }
}
