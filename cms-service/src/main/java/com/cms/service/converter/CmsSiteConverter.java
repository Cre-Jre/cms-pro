package com.cms.service.converter;

import com.cms.dao.entity.CmsSiteEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsSiteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EnumConverter.class})
public interface CmsSiteConverter {
    CmsSiteConverter CONVERTER = Mappers.getMapper(CmsSiteConverter.class);

    CmsSiteEntity dtoToEntity(CmsSiteDto dto);

    CmsSiteDto entityToDto(CmsSiteEntity entity);
}
