package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsSiteService;
import com.cms.service.api.CmsStaticPageService;
import com.cms.service.dto.CmsSiteDto;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("static")
public class StaticPageController {
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsStaticPageService cmsStaticPageService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("static","index");
    }

    @PostMapping("index.do")
    @ResponseBody
    public Result<String> doIndexStatic(){
        CmsSiteDto cmsSite = cmsSiteService.get();
        //站点是否开启首页静态化
        Boolean staticIndex = cmsSite.getStaticIndex();
        if(BooleanUtils.isFalse(staticIndex)){
            return Result.failed("请先在站点设置中开启静态化首页");
        }
        cmsStaticPageService.staticIndex();
        return Result.success();
    }

    @PostMapping("deleteIndex.do")
    @ResponseBody
    public Result<String> doDeleteIndex(){
        boolean result = cmsStaticPageService.deleteIndex();
        if(BooleanUtils.isTrue(result)){
            return Result.success();
        }
        return Result.failed("删除失败");
    }
}
