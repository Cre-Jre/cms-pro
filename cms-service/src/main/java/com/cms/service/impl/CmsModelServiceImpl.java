package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsModelEntity;
import com.cms.dao.entity.CmsRoleEntity;
import com.cms.dao.mapper.CmsModelMapper;
import com.cms.service.api.CmsModelService;
import com.cms.service.converter.CmsModelConverter;
import com.cms.service.converter.CmsRoleConverter;
import com.cms.service.dto.CmsModelDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsModelServiceImpl implements CmsModelService {

    @Autowired
    private CmsModelMapper cmsModelMapper;

    @Override
    public void save(CmsModelDto dto) {
        cmsModelMapper.save(CmsModelConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsModelDto dto) {

    }

    @Override
    public CmsModelDto getById(Integer id) {
        return CmsModelConverter.CONVERTER.entityToDto(cmsModelMapper.selectById(id));
    }

    @Override
    public Page<CmsModelDto> getPage(CmsModelDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsModelConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsModelEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsModelMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsModelConverter.CONVERTER.entityToDto(page.getResult()));
    }
}
