package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.contex.utils.UtilsServletContext;
import com.cms.core.exception.BusinessException;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsChannelService;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.CmsStaticPageService;
import com.cms.service.dto.CmsChannelDto;
import com.cms.service.dto.CmsContentDto;
import com.cms.service.dto.CmsSiteDto;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Slf4j
@Service
public class CmsStaticPageServiceImpl implements CmsStaticPageService {

    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private UtilsServletContext utilsServletContext;

    @Override
    public void staticIndex() {
        CmsSiteDto cmsSite = cmsSiteService.get();
        //站点是否开启首页静态化
        Boolean staticIndex = cmsSite.getStaticIndex();
        if(BooleanUtils.isFalse(staticIndex)){
            return;
        }
        //静态化存放目录
        String staticDir = cmsSite.getStaticDir();
        if(StringUtils.isEmpty(staticDir)){
            throw new BusinessException("请先在站点设置中填写静态页目录");
        }
        //首页模板路径
        String templatePath = cmsSite.getTplIndex();
        //输出路径
        String outPutPath = staticDir +"/index"+StaticWebSuffixEnum.HTML.getLabel();
        //获取基于项目的完整路径
        String realOutPutPath=utilsServletContext.getRealPath(outPutPath);
        log.info("realOutPutPath=[{}]",realOutPutPath);
        File realOutPutPathFile = new File(realOutPutPath);
        File parentDir = realOutPutPathFile.getParentFile();
        if(!parentDir.exists()){
            parentDir.mkdirs();
        }
        HashMap<String, Object> data = Maps.newHashMap();
        data.put("site",cmsSite);
        data.put("basePath",utilsServletContext.getContextPath());
        try(Writer writer=new OutputStreamWriter(new FileOutputStream(realOutPutPathFile), StandardCharsets.UTF_8)){
            Configuration configuration = freeMarkerConfig.getConfiguration();
            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            Template template = configuration.getTemplate(templatePath);
            template.process(data,writer);
        }catch (Exception e){
            log.error("staticIndex生成首页模板失败=[{}]",e.getMessage());
            throw new BusinessException("生成首页模板失败");
        }
    }

    @Override
    public boolean deleteIndex() {
        CmsSiteDto cmsSite = cmsSiteService.get();
        String staticDir = cmsSite.getStaticDir();
        HttpServletRequest request = UtilsHttp.getRequest();
        String contextPath = request.getContextPath();
        File file = new File(utilsServletContext.getRealPath(contextPath + "/" + staticDir + "/index" + StaticWebSuffixEnum.HTML.getLabel()));
        return file.delete();
    }

    @Override
    public void staticContent(CmsContentDto cmsContentDto, Configuration configuration) {
        CmsSiteDto cmsSite = cmsSiteService.get();
        //静态化存放目录
        String staticDir = cmsSite.getStaticDir();
        if(StringUtils.isEmpty(staticDir)){
            throw new BusinessException("请先在站点设置中填写静态页目录");
        }
        String contextPath = utilsServletContext.getContextPath();
        Integer channelId = cmsContentDto.getChannelId();
        CmsChannelDto cmsChannelDto = cmsChannelService.getById(channelId);
        StaticWebSuffixEnum staticSuffix = cmsSite.getStaticSuffix();
        //模板路径
        String templatePath = "/front/default/content/news.html";
        //输出目录   20201112
        String outputPath = staticDir+"/"+cmsChannelDto.getPath()+ LocalDate.now().format(DateTimeFormatter.ofPattern("/yyyyMMdd"))+"/"+
                cmsContentDto.getId()+staticSuffix.getLabel();
        log.info("staticContent---outPutPath=[{}]",outputPath);
        String realOutPutPath = utilsServletContext.getRealPath(outputPath);
        log.info("staticContent---realOutPutPath=[{}]",outputPath);
        File realOutPutFile = new File(realOutPutPath);
        File parentFile = realOutPutFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        HashMap<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("basePath",contextPath);
        dataMap.put("title",cmsContentDto.getTitle());
        dataMap.put("content",cmsContentDto.getContent());
        dataMap.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        try(Writer writer=new OutputStreamWriter(new FileOutputStream(realOutPutFile), StandardCharsets.UTF_8)){
            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            Template template = configuration.getTemplate(templatePath);
            template.process(dataMap,writer);
        }catch (Exception e){
            log.error("staticIndex生成首页模板失败=[{}]",e.getMessage());
            throw new BusinessException("生成内容模板失败");
        }

    }
}
