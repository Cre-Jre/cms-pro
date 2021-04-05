package com.cms.service.converter;

import com.cms.dao.entity.CmsModelItemEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsModelItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EnumConverter.class})
public interface CmsModelItemConverter {
    CmsModelItemConverter CONVERTER = Mappers.getMapper(CmsModelItemConverter.class);

    CmsModelItemDto entityToDto(CmsModelItemEntity entity);

    CmsModelItemEntity dtoToEntity(CmsModelItemDto dto);
}
