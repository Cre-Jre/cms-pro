package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.mapper.CmsModelItemMapper;
import com.cms.service.api.CmsModelItemService;
import com.cms.service.converter.CmsModelItemConverter;
import com.cms.service.dto.CmsModelItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsModelItemServiceImpl implements CmsModelItemService {

    @Autowired
    private CmsModelItemMapper cmsModelItemMapper;

    @Override
    public void save(CmsModelItemDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsModelItemDto dto) {

    }

    @Override
    public CmsModelItemDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsModelItemDto> getPage(CmsModelItemDto dto) {
        return null;
    }

    @Override
    public void batchInsert(List<CmsModelItemDto> list) {
        cmsModelItemMapper.batchInsert(CmsModelItemConverter.CONVERTER.dtoToEntity(list));
    }

    @Override
    public List<CmsModelItemDto> getByModelIdAndChannelModel(Integer modelId, Boolean channelModel) {
        return CmsModelItemConverter.CONVERTER.entityToDto(cmsModelItemMapper.selectByModelIdAndChannelModel(modelId,channelModel));
    }
}
