package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.core.foundation.Page;
import com.cms.service.api.CmsModelService;
import com.cms.service.dto.CmsModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("delete.do")
    @ResponseBody
    public Result<String> doDelete(Integer id){
        cmsModelService.deleteById(id);
        return Result.success();
    }

    @GetMapping("edit.do")
    public String toEdit(Integer id, Model model){
        model.addAttribute("data",cmsModelService.getById(id));
        return UtilsTemplate.adminTemplate("model","edit");
    }

    @PostMapping("edit.do")
    @ResponseBody
    public Result<String> doEdit(CmsModelDto dto){
        cmsModelService.update(dto);
        return Result.success();
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result<Page<CmsModelDto>> doPage(CmsModelDto dto){
        return Result.success(cmsModelService.getPage(dto));
    }
}
