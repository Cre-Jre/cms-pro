package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.mapper.CmsChannelMapper;
import com.cms.service.api.CmsChannelService;
import com.cms.service.converter.CmsChannelConverter;
import com.cms.service.dto.CmsChannelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsChannelServiceImpl implements CmsChannelService {

    @Autowired
    private CmsChannelMapper cmsChannelMapper;

    @Override
    public void save(CmsChannelDto dto) {
        cmsChannelMapper.save(CmsChannelConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsChannelDto dto) {

    }

    @Override
    public CmsChannelDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsChannelDto> getPage(CmsChannelDto dto) {
        return null;
    }

    @Override
    public List<CmsChannelDto> getList(CmsChannelDto cmsChannelDto) {
        return CmsChannelConverter.CONVERTER.entityToDto(cmsChannelMapper.selectBySearchProvider(SearchProvider.of(CmsChannelConverter.CONVERTER.dtoToEntity(cmsChannelDto))));
    }
}
