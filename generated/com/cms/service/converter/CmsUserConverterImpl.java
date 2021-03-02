package com.cms.service.converter;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.dao.enums.converter.EnumConverter;
import com.cms.service.dto.CmsUserDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-02T09:05:29+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class CmsUserConverterImpl implements CmsUserConverter {

    @Override
    public CmsUserEntity dtoToEntity(CmsUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        CmsUserEntity cmsUserEntity = new CmsUserEntity();

        cmsUserEntity.setId( dto.getId() );
        cmsUserEntity.setCreateTime( dto.getCreateTime() );
        cmsUserEntity.setUpdateTime( dto.getUpdateTime() );
        cmsUserEntity.setUsername( dto.getUsername() );
        cmsUserEntity.setStatus( EnumConverter.toInteger( dto.getStatus() ) );
        cmsUserEntity.setAdmin( dto.getAdmin() );
        cmsUserEntity.setLastLoginIp( dto.getLastLoginIp() );
        cmsUserEntity.setSessionId( dto.getSessionId() );
        cmsUserEntity.setAdministrator( dto.getAdministrator() );

        return cmsUserEntity;
    }

    @Override
    public CmsUserDto entityToDto(CmsUserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CmsUserDto cmsUserDto = new CmsUserDto();

        cmsUserDto.setId( entity.getId() );
        cmsUserDto.setCreateTime( entity.getCreateTime() );
        cmsUserDto.setUpdateTime( entity.getUpdateTime() );
        cmsUserDto.setUsername( entity.getUsername() );
        if ( entity.getStatus() != null ) {
            cmsUserDto.setStatus( EnumConverter.toUserStatusEnum( entity.getStatus().intValue() ) );
        }
        cmsUserDto.setAdmin( entity.getAdmin() );
        cmsUserDto.setLastLoginIp( entity.getLastLoginIp() );
        cmsUserDto.setSessionId( entity.getSessionId() );
        cmsUserDto.setAdministrator( entity.getAdministrator() );

        return cmsUserDto;
    }
}
