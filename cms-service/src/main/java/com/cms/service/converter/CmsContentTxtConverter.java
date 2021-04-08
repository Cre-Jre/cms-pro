package com.cms.service.converter;

import com.cms.dao.entity.CmsContentTxtEntity;
import com.cms.service.dto.CmsContentTxtDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsContentTxtConverter {
    CmsContentTxtConverter CONVERTER = Mappers.getMapper(CmsContentTxtConverter.class);

    CmsContentTxtDto entityToDto(CmsContentTxtEntity entity);

    CmsContentTxtEntity dtoToEntity(CmsContentTxtDto dto);

}
