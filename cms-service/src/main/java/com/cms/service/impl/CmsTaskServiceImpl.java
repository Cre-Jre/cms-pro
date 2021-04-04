package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.mapper.CmsTaskMapper;
import com.cms.service.api.CmsTaskService;
import com.cms.service.converter.CmsTaskConverter;
import com.cms.service.dto.CmsTaskDto;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsTaskServiceImpl implements CmsTaskService {

    @Autowired
    private CmsTaskMapper cmsTaskMapper;

    @Override
    public void save(CmsTaskDto dto) {
        CmsTaskEntity cmsTaskEntity = CmsTaskConverter.CONVERTER.dtoToEntity(dto);
        cmsTaskMapper.save(cmsTaskEntity);
        if(BooleanUtils.isTrue(dto.getEnable())){

        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsTaskDto dto) {

    }

    @Override
    public CmsTaskDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsTaskDto> getPage(CmsTaskDto dto) {
        return null;
    }
}
