package com.cms.service.converter;

import com.cms.dao.entity.CmsRolePermissionEntity;
import com.cms.service.dto.CmsRolePermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsRolePermissionConverter {
    CmsRolePermissionConverter CONVERTER = Mappers.getMapper(CmsRolePermissionConverter.class);

    CmsRolePermissionEntity dtoToEntity(CmsRolePermissionDto dto);

    CmsRolePermissionDto entityToDto(CmsRolePermissionEntity entity);

}
