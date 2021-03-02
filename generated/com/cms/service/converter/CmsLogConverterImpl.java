package com.cms.service.converter;

import com.cms.dao.entity.CmsLogEntity;
import com.cms.service.dto.CmsLogDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-02T09:05:29+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class CmsLogConverterImpl implements CmsLogConverter {

    @Override
    public CmsLogEntity dtoToEntity(CmsLogDto dto) {
        if ( dto == null ) {
            return null;
        }

        CmsLogEntity cmsLogEntity = new CmsLogEntity();

        cmsLogEntity.setId( dto.getId() );
        cmsLogEntity.setCreateTime( dto.getCreateTime() );
        cmsLogEntity.setUpdateTime( dto.getUpdateTime() );
        cmsLogEntity.setUserId( dto.getUserId() );
        cmsLogEntity.setUsername( dto.getUsername() );
        cmsLogEntity.setLoginIp( dto.getLoginIp() );
        cmsLogEntity.setUrl( dto.getUrl() );
        cmsLogEntity.setContent( dto.getContent() );

        return cmsLogEntity;
    }

    @Override
    public CmsLogDto entityToDto(CmsLogEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CmsLogDto cmsLogDto = new CmsLogDto();

        cmsLogDto.setId( entity.getId() );
        cmsLogDto.setCreateTime( entity.getCreateTime() );
        cmsLogDto.setUpdateTime( entity.getUpdateTime() );
        cmsLogDto.setUserId( entity.getUserId() );
        cmsLogDto.setUsername( entity.getUsername() );
        cmsLogDto.setLoginIp( entity.getLoginIp() );
        cmsLogDto.setUrl( entity.getUrl() );
        cmsLogDto.setContent( entity.getContent() );

        return cmsLogDto;
    }
}
