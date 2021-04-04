package com.cms.service.converter;

import com.cms.dao.entity.CmsTopicEntity;
import com.cms.service.dto.CmsTopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsTopicConverter {
    CmsTopicConverter CONVERTER = Mappers.getMapper(CmsTopicConverter.class);

    CmsTopicDto entityToDto(CmsTopicEntity entity);

    CmsTopicEntity dtoToEntity(CmsTopicDto dto);

    List<CmsTopicDto> entityToDto(List<CmsTopicEntity> entities);
}
