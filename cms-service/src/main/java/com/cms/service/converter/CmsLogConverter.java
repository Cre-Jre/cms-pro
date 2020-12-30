package com.cms.service.converter;

import com.cms.dao.entity.CmsLogEntity;
import com.cms.service.dto.CmsLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsLogConverter {
    CmsLogConverter CONVERTER = Mappers.getMapper(CmsLogConverter.class);

    CmsLogEntity dtoToEntity(CmsLogDto dto);
    CmsLogDto entityToDto(CmsLogEntity entity);
}
