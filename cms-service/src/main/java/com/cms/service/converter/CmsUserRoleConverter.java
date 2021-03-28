package com.cms.service.converter;

import com.cms.dao.entity.CmsUserRoleEntity;
import com.cms.service.dto.CmsUserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsUserRoleConverter {
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);

    CmsUserRoleEntity dtoToEntity(CmsUserRoleDto dto);

    CmsUserRoleDto entityToDto(CmsUserRoleEntity entity);
}
