package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.mapper.CmsTopicMapper;
import com.cms.service.api.CmsTopicService;
import com.cms.service.converter.CmsTopicConverter;
import com.cms.service.dto.CmsTopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsTopicServiceImpl implements CmsTopicService {

    @Autowired
    private CmsTopicMapper cmsTopicMapper;

    @Override
    public void save(CmsTopicDto dto) {
        cmsTopicMapper.save(CmsTopicConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsTopicDto dto) {

    }

    @Override
    public CmsTopicDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsTopicDto> getPage(CmsTopicDto dto) {
        return null;
    }
}
