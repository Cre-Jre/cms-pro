package com.cms.service.converter;

import com.cms.dao.entity.CmsContentTopicEntity;
import com.cms.service.dto.CmsContentTopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsContentTopicConverter {
    CmsContentTopicConverter CONVERTER = Mappers.getMapper(CmsContentTopicConverter.class);

    CmsContentTopicDto entityToDto(CmsContentTopicEntity entity);

    CmsContentTopicEntity dtoToEntity(CmsContentTopicDto dto);
}
