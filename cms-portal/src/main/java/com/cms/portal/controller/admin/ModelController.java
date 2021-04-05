package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("model")
public class ModelController {

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("model","index");
    }

    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("model","add");
    }


}
