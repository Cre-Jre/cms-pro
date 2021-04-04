package com.cms.service.converter;


import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsTaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {EnumConverter.class})
public interface CmsTaskConverter {
    CmsTaskConverter CONVERTER = Mappers.getMapper(CmsTaskConverter.class);

    CmsTaskEntity dtoToEntity(CmsTaskDto dto);

    CmsTaskDto entityToDto(CmsTaskEntity entity);

    List<CmsTaskDto> entityToDto(List<CmsTaskEntity> entities);
}
