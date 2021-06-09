package com.cms.service.converter;

import com.cms.dao.entity.CmsUserEntity;
import com.cms.service.dto.CmsUserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-09T09:26:23+0800",
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
        cmsUserEntity.setStatus( dto.getStatus() );
        cmsUserEntity.setAdmin( dto.getAdmin() );
        cmsUserEntity.setPassword( dto.getPassword() );
        cmsUserEntity.setSalt( dto.getSalt() );
        cmsUserEntity.setEmail( dto.getEmail() );
        cmsUserEntity.setRoleName( dto.getRoleName() );
        cmsUserEntity.setRegisterTime( dto.getRegisterTime() );
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
        cmsUserDto.setStatus( entity.getStatus() );
        cmsUserDto.setAdmin( entity.getAdmin() );
        cmsUserDto.setPassword( entity.getPassword() );
        cmsUserDto.setSalt( entity.getSalt() );
        cmsUserDto.setEmail( entity.getEmail() );
        cmsUserDto.setRoleName( entity.getRoleName() );
        cmsUserDto.setRegisterTime( entity.getRegisterTime() );
        cmsUserDto.setAdministrator( entity.getAdministrator() );

        return cmsUserDto;
    }

    @Override
    public List<CmsUserDto> entityToDto(List<CmsUserEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<CmsUserDto> list = new ArrayList<CmsUserDto>( entity.size() );
        for ( CmsUserEntity cmsUserEntity : entity ) {
            list.add( entityToDto( cmsUserEntity ) );
        }

        return list;
    }
}
