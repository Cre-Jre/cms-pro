package com.cms.service.converter;

import com.cms.dao.entity.CmsModelEntity;
import com.cms.service.dto.CmsModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsModelConverter {
    CmsModelConverter CONVERTER = Mappers.getMapper(CmsModelConverter.class);

    CmsModelDto entityToDto(CmsModelEntity entity);

    CmsModelEntity dtoToEntity(CmsModelDto dto);

}
