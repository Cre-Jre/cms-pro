package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.service.api.CmsTaskService;
import com.cms.service.dto.CmsTaskDto;
import org.springframework.stereotype.Service;

@Service
public class CmsTaskServiceImpl implements CmsTaskService {
    @Override
    public void save(CmsTaskDto dto) {

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
