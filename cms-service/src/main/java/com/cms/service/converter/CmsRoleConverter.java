package com.cms.service.converter;

import com.cms.dao.entity.CmsRoleEntity;
import com.cms.service.dto.CmsRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsRoleConverter {
    CmsRoleConverter CONVERTER = Mappers.getMapper(CmsRoleConverter.class);

    CmsRoleEntity dtoToEntity(CmsRoleDto dto);

    CmsRoleDto entityToDto(CmsRoleEntity entity);
}
