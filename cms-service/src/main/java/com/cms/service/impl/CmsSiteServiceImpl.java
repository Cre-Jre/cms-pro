package com.cms.service.impl;

import com.cms.dao.mapper.CmsSiteMapper;
import com.cms.service.api.CmsSiteService;
import com.cms.service.converter.CmsSiteConverter;
import com.cms.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    @Autowired
    private CmsSiteMapper cmsSiteMapper;

    @Override
    public void save(CmsSiteDto dto) {

    }

    @Override
    public void update(CmsSiteDto dto) {
        cmsSiteMapper.update(CmsSiteConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsSiteDto getById(Integer id) {
        return null;
    }

    @Override
    public CmsSiteDto get() {
        return CmsSiteConverter.CONVERTER.entityToDto(cmsSiteMapper.selectById(1));
    }
}
