package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsModelService;
import com.cms.service.dto.CmsModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("model")
public class ModelController {

    @Autowired
    private CmsModelService cmsModelService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("model","index");
    }

    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("model","add");
    }

    @PostMapping("add.do")
    @ResponseBody
    public Result<String> doAdd(CmsModelDto dto){
        cmsModelService.save(dto);
        return Result.success();
    }
}
