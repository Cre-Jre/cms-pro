package com.cms.service.impl;

import com.cms.dao.mapper.CmsLogMapper;
import com.cms.service.api.CmsLogService;
import com.cms.service.converter.CmsLogConverter;
import com.cms.service.dto.CmsLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsLogServiceImpl implements CmsLogService {
    @Autowired
    private CmsLogMapper cmsLogMapper;

    @Override
    public void save(CmsLogDto dto) {
        cmsLogMapper.save(CmsLogConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void update(CmsLogDto dto) {

    }

    @Override
    public CmsLogDto getById(Integer id) {
        return null;
    }
}
