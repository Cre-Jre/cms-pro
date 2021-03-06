package com.cms.service.converter;

import com.cms.dao.entity.CmsModelItemEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsModelItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EnumConverter.class})
public interface CmsModelItemConverter {
    CmsModelItemConverter CONVERTER = Mappers.getMapper(CmsModelItemConverter.class);

    CmsModelItemDto entityToDto(CmsModelItemEntity entity);

    CmsModelItemEntity dtoToEntity(CmsModelItemDto dto);

    List<CmsModelItemEntity> dtoToEntity(List<CmsModelItemDto> dto);

    List<CmsModelItemDto> entityToDto(List<CmsModelItemEntity> entity);

}
