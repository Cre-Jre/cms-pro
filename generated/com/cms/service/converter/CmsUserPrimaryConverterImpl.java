package com.cms.service.converter;

import com.cms.dao.entity.CmsUserPrimaryEntity;
import com.cms.service.dto.CmsUserPrimaryDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-02T09:05:28+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class CmsUserPrimaryConverterImpl implements CmsUserPrimaryConverter {

    @Override
    public CmsUserPrimaryDto entityToDto(CmsUserPrimaryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CmsUserPrimaryDto cmsUserPrimaryDto = new CmsUserPrimaryDto();

        cmsUserPrimaryDto.setId( entity.getId() );
        cmsUserPrimaryDto.setCreateTime( entity.getCreateTime() );
        cmsUserPrimaryDto.setUpdateTime( entity.getUpdateTime() );
        cmsUserPrimaryDto.setUsername( entity.getUsername() );
        cmsUserPrimaryDto.setPassword( entity.getPassword() );
        cmsUserPrimaryDto.setSalt( entity.getSalt() );
        cmsUserPrimaryDto.setEmail( entity.getEmail() );
        cmsUserPrimaryDto.setLoginCount( entity.getLoginCount() );

        return cmsUserPrimaryDto;
    }
}
