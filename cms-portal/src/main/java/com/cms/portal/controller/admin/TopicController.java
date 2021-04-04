package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsServletContext;
import com.cms.contex.utils.UtilsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("topic")
public class TopicController {

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


}
