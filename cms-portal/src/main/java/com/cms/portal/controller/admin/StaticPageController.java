package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("static")
public class StaticPageController {

    @GetMapping("index.do")
    public Result<String> doIndexStatic(){

        return Result.success();
    }


}
