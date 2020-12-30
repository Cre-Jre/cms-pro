package com.cms.service.converter;

import com.cms.dao.entity.CmsSiteEntity;
import com.cms.service.dto.CmsSiteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsSiteConverter {
    CmsSiteConverter CONVERTER = Mappers.getMapper(CmsSiteConverter.class);
    CmsSiteEntity dtoToEntity(CmsSiteDto dto);
    CmsSiteDto entityToDto(CmsSiteEntity entity);

}
