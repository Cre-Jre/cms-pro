package com.cms.service.converter;

import com.cms.dao.entity.CmsContentEntity;
import com.cms.service.dto.CmsContentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsContentConverter {
    CmsContentConverter CONVERTER = Mappers.getMapper(CmsContentConverter.class);

    CmsContentDto entityToDto(CmsContentEntity entity);

    CmsContentEntity dtoToEntity(CmsContentDto dto);

    List<CmsContentDto> entityToDto(List<CmsContentEntity> entities);
}
