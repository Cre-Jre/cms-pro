package com.cms.service.impl;

import com.cms.contex.utils.UtilsServletContext;
import com.cms.core.exception.BusinessException;
import com.cms.dao.enums.StaticWebSuffixEnum;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.CmsStaticPageService;
import com.cms.service.dto.CmsSiteDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

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
        String templatePath = "/front/default/index"+staticSuffix.getLabel();
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
    }
}
