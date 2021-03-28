package com.cms.portal.controller.front;

import com.cms.contex.utils.UtilsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DynamicPageController {

    @GetMapping("index.shtml")
    public String index(){
        return UtilsTemplate.frontTemplate("index");
    }
}
