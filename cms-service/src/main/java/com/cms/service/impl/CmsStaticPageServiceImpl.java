package com.cms.service.impl;

import com.cms.contex.utils.UtilsHttp;
import com.cms.contex.utils.UtilsServletContext;
import com.cms.core.exception.BusinessException;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.CmsStaticPageService;
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
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Slf4j
@Service
public class CmsStaticPageServiceImpl implements CmsStaticPageService {

    @Autowired
    private CmsSiteService cmsSiteService;
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
        //获取静态后缀
        StaticWebSuffixEnum staticSuffix = cmsSite.getStaticSuffix();
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
        HttpServletRequest request = UtilsHttp.getRequest();
        HashMap<String, Object> data = Maps.newHashMap();
        data.put("site",cmsSite);
        data.put("basePath",request.getContextPath());
        try(Writer writer=new OutputStreamWriter(new FileOutputStream(realOutPutPathFile), StandardCharsets.UTF_8)){
            WebApplicationContext webApplicationContext = UtilsHttp.getWebApplicationContext(UtilsHttp.getRequest());
            FreeMarkerConfigurer freeMarkerConfigurer = webApplicationContext.getBean(FreeMarkerConfigurer.class);
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
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
}
