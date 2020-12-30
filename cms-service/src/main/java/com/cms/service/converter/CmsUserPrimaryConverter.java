package com.cms.service.converter;

import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.service.dto.CmsUserPrimaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsUserPrimaryConverter {
    CmsUserPrimaryConverter CONVERTER = Mappers.getMapper(CmsUserPrimaryConverter.class);

    CmsUserPrimaryDto entityToDto(CmsUserPrimaryEntity entity);
}
