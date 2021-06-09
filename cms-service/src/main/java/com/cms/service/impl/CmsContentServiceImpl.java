package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.core.foundation.Page;
import com.cms.core.foundation.SearchProvider;
import com.cms.dao.entity.CmsContentEntity;
import com.cms.dao.mapper.CmsContentMapper;
import com.cms.dao.mapper.CmsContentTopicMapper;
import com.cms.dao.mapper.CmsContentTxtMapper;
import com.cms.service.api.CmsContentService;
import com.cms.service.api.CmsStaticPageService;
import com.cms.service.converter.CmsContentConverter;
import com.cms.service.converter.CmsContentTopicConverter;
import com.cms.service.converter.CmsContentTxtConverter;
import com.cms.service.dto.CmsContentDto;
import com.cms.service.dto.CmsContentTopicDto;
import com.cms.service.dto.CmsContentTxtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.List;

@Service
public class CmsContentServiceImpl implements CmsContentService {

    @Autowired
    private CmsContentMapper cmsContentMapper;
    @Autowired
    private CmsContentTxtMapper cmsContentTxtMapper;
    @Autowired
    private CmsStaticPageService cmsStaticPageService;
    @Autowired
    private CmsContentTopicMapper cmsContentTopicMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(CmsContentDto dto) {
        //content表
        CmsContentEntity cmsContentEntity = CmsContentConverter.CONVERTER.dtoToEntity(dto);
        cmsContentMapper.save(cmsContentEntity);

        //content_txt表
        Integer id = cmsContentEntity.getId();
        dto.setId(id);
        CmsContentTxtDto cmsContentTxtDto = new CmsContentTxtDto();
        cmsContentTxtDto.setContentId(id);
        cmsContentTxtDto.setContent(dto.getContent());
        cmsContentTxtMapper.save(CmsContentTxtConverter.CONVERTER.dtoToEntity(cmsContentTxtDto));

        //content_topic表
        CmsContentTopicDto cmsContentTopicDto = new CmsContentTopicDto();
        cmsContentTopicDto.setContentId(id);
        cmsContentTopicDto.setTopicId(dto.getTopicId());
        cmsContentTopicMapper.save(CmsContentTopicConverter.CONVERTER.dtoToEntity(cmsContentTopicDto));
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

    @Override
    public void afterOperationStatus(CmsContentDto cmsContentDto) {
        WebApplicationContext webApplicationContext = UtilsHttp.getWebApplicationContext(UtilsHttp.getRequest());
        FreeMarkerConfigurer freeMarkerConfigurer = webApplicationContext.getBean(FreeMarkerConfigurer.class);
        cmsStaticPageService.staticContent(cmsContentDto,freeMarkerConfigurer.getConfiguration());
    }

    @Override
    public List<CmsContentDto> getListByDirective(SearchProvider.Inner inner) {
        return CmsContentConverter.CONVERTER.entityToDto(cmsContentMapper.getListByDirective(inner));
    }
}
