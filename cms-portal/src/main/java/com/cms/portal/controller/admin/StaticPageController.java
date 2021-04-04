package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsStaticPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("static")
public class StaticPageController {

    @Autowired
    private CmsStaticPageService cmsStaticPageService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("static","index");
    }

    @PostMapping("index.do")
    @ResponseBody
    public Result<String> doIndexStatic(){
        cmsStaticPageService.staticIndex();
        return Result.success();
    }


}
