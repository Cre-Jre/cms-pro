package com.cms.service.converter;

import com.cms.dao.entity.CmsSiteEntity;
import com.cms.service.dto.CmsSiteDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-02T09:05:28+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class CmsSiteConverterImpl implements CmsSiteConverter {

    @Override
    public CmsSiteEntity dtoToEntity(CmsSiteDto dto) {
        if ( dto == null ) {
            return null;
        }

        CmsSiteEntity cmsSiteEntity = new CmsSiteEntity();

        cmsSiteEntity.setId( dto.getId() );
        cmsSiteEntity.setCreateTime( dto.getCreateTime() );
        cmsSiteEntity.setUpdateTime( dto.getUpdateTime() );
        cmsSiteEntity.setSiteName( dto.getSiteName() );
        cmsSiteEntity.setKeywords( dto.getKeywords() );
        cmsSiteEntity.setDescription( dto.getDescription() );

        return cmsSiteEntity;
    }

    @Override
    public CmsSiteDto entityToDto(CmsSiteEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CmsSiteDto cmsSiteDto = new CmsSiteDto();

        cmsSiteDto.setId( entity.getId() );
        cmsSiteDto.setCreateTime( entity.getCreateTime() );
        cmsSiteDto.setUpdateTime( entity.getUpdateTime() );
        cmsSiteDto.setSiteName( entity.getSiteName() );
        cmsSiteDto.setKeywords( entity.getKeywords() );
        cmsSiteDto.setDescription( entity.getDescription() );

        return cmsSiteDto;
    }
}
