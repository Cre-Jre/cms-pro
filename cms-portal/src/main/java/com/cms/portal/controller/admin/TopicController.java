package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsServletContext;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.foundation.Page;
import com.cms.service.api.CmsTopicService;
import com.cms.service.dto.CmsTopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Controller
@RequestMapping("topic")
public class TopicController {

    @Autowired
    private CmsTopicService cmsTopicService;
    @Autowired
    private UtilsServletContext utilsServletContext;

    @GetMapping("index.do")
    public String toindex(){
        return UtilsTemplate.adminTemplate("topic","index");
    }

    @GetMapping("add.do")
    public String toAdd(Model model){
        model.addAttribute("template",utilsServletContext.getTplRelativePath("topic","topic"));
        return UtilsTemplate.adminTemplate("topic","add");
    }

    @PostMapping("add.do")
    @ResponseBody
    public Result<String> doAdd(CmsTopicDto dto){
        cmsTopicService.save(dto);
        return Result.success();
    }

    @PostMapping("delete.do")
    @ResponseBody
    @DoLog(content = "删除专题")
    public Result<String> doDelete(@NotNull(message = "请输入id") Integer id){
        cmsTopicService.deleteById(id);
        return Result.success();
    }

    @GetMapping("edit.do")
    public String toEdit(Integer id,Model model){
        model.addAttribute("data",cmsTopicService.getById(id));
        model.addAttribute("template",utilsServletContext.getTplRelativePath("topic","topic"));
        return UtilsTemplate.adminTemplate("topic","edit");
    }

    @PostMapping("edit.do")
    @ResponseBody
    public Result<String> doEdit(CmsTopicDto dto){
        cmsTopicService.update(dto);
        return Result.success();
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result<Page<CmsTopicDto>> doPage(CmsTopicDto dto){
        return Result.success(cmsTopicService.getPage(dto));
    }

    @PostMapping("list.do")
    @ResponseBody
    public Result doList(CmsTopicDto dto){
        return Result.success((ArrayList)cmsTopicService.getList(dto));
    }
}
