package com.cms.portal.controller.admin;

import com.cms.contex.utils.UtilsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("index");
    }
}
