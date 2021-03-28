package com.cms.service.impl;

import com.cms.core.foundation.Page;
import com.cms.dao.mapper.CmsFriendLinkMapper;
import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.converter.CmsFriendLinkConverter;
import com.cms.service.dto.CmsFriendLinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsFriendLinkServiceImpl implements CmsFriendLinkService {

    @Autowired
    private CmsFriendLinkMapper cmsFriendLinkMapper;

    @Override
    public void save(CmsFriendLinkDto dto) {
        cmsFriendLinkMapper.save(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsFriendLinkDto dto) {

    }

    @Override
    public CmsFriendLinkDto getById(Integer id) {
        return null;
    }

    @Override
    public Page<CmsFriendLinkDto> getPage(CmsFriendLinkDto dto) {
        return null;
    }
}
