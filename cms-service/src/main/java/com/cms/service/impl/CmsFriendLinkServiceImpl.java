package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsFriendLinkEntity;
import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.mapper.CmsFriendLinkMapper;
import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.converter.CmsFriendLinkConverter;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.dto.CmsFriendLinkDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        cmsFriendLinkMapper.deleteById(id);
    }

    @Override
    public void update(CmsFriendLinkDto dto) {
        cmsFriendLinkMapper.update(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsFriendLinkDto getById(Integer id) {
        return CmsFriendLinkConverter.CONVERTER.entityToDto(cmsFriendLinkMapper.selectById(id));
    }

    @Override
    public Page<CmsFriendLinkDto> getPage(CmsFriendLinkDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsFriendLinkConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsFriendLinkEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsFriendLinkMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsFriendLinkConverter.CONVERTER.entityToDto(page.getResult()));
    }

    @Override
    public List<CmsFriendLinkDto> getList(CmsFriendLinkDto cmsFriendLinkDto) {
        SearchProvider of = SearchProvider.of(CmsFriendLinkConverter.CONVERTER.dtoToEntity(cmsFriendLinkDto));
        return CmsFriendLinkConverter.CONVERTER.entityToDto(cmsFriendLinkMapper.selectBySearchProvider(of));
    }
}
