package com.cms.service.converter;

import com.cms.dao.entity.CmsChannelEntity;
import com.cms.service.dto.CmsChannelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsChannelConverter {
    CmsChannelConverter CONVERTER = Mappers.getMapper(CmsChannelConverter.class);

    CmsChannelEntity dtoToEntity(CmsChannelDto dto);

    CmsChannelDto entityToDto(CmsChannelEntity entity);

    List<CmsChannelDto> entityToDto(List<CmsChannelEntity> entities);
}
