package com.cms.service.converter;

import com.cms.dao.entity.CmsFriendLinkEntity;
import com.cms.service.dto.CmsFriendLinkDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsFriendLinkConverter {
    CmsFriendLinkConverter CONVERTER = Mappers.getMapper(CmsFriendLinkConverter.class);

    CmsFriendLinkEntity dtoToEntity(CmsFriendLinkDto dto);

    CmsFriendLinkDto entityToDto(CmsFriendLinkEntity entity);

    List<CmsFriendLinkDto> entityToDto(List<CmsFriendLinkEntity> entity);
}
