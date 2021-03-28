package com.cms.service.converter;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = EnumConverter.class)
public interface CmsUserConverter {
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);

    CmsUserEntity dtoToEntity(CmsUserDto dto);

    CmsUserDto entityToDto(CmsUserEntity entity);

    List<CmsUserDto> entityToDto(List<CmsUserEntity> entity);
}
