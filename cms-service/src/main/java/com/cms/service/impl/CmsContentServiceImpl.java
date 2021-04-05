package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.service.api.CmsContentService;
import com.cms.service.dto.CmsContentDto;
import org.springframework.stereotype.Service;

@Service
public class CmsContentServiceImpl implements CmsContentService {
    @Override
    public void save(CmsContentDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsContentDto dto) {

    }

    @Override
    public CmsContentDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsContentDto> getPage(CmsContentDto dto) {
        return null;
    }
}
