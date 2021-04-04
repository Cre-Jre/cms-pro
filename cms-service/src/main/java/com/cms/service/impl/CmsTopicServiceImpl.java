package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsTaskEntity;
import com.cms.dao.entity.CmsTopicEntity;
import com.cms.dao.mapper.CmsTopicMapper;
import com.cms.service.api.CmsTopicService;
import com.cms.service.converter.CmsTaskConverter;
import com.cms.service.converter.CmsTopicConverter;
import com.cms.service.dto.CmsTopicDto;
import com.github.pagehelper.PageHelper;
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
        cmsTopicMapper.update(CmsTopicConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsTopicDto getById(Integer id) {
        return CmsTopicConverter.CONVERTER.entityToDto(cmsTopicMapper.selectById(id));
    }

    @Override
    public Page<CmsTopicDto> getPage(CmsTopicDto dto) {
        UtilsHttp.Page pageInfo = UtilsHttp.getPageInfo();
        SearchProvider of = SearchProvider.of(CmsTopicConverter.CONVERTER.dtoToEntity(dto));
        com.github.pagehelper.Page<CmsTopicEntity> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).
                doSelectPage(() -> cmsTopicMapper.selectBySearchProvider(of));
        return new Page<>(page.getTotal(),CmsTopicConverter.CONVERTER.entityToDto(page.getResult()));
    }
}
