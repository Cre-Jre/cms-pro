package com.cms.service.converter;

import com.cms.dao.entity.CmsTopicEntity;
import com.cms.service.dto.CmsTopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsTopicConverter {
    CmsTopicConverter CONVERTER = Mappers.getMapper(CmsTopicConverter.class);

    CmsTopicDto entityToDto(CmsTopicEntity entity);

    CmsTopicEntity dtoToEntity(CmsTopicDto dto);
}
