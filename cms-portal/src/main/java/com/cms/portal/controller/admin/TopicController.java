package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsServletContext;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsTopicService;
import com.cms.service.dto.CmsTopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
