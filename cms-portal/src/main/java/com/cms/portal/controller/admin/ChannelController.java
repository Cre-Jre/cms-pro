package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsChannelService;
import com.cms.service.dto.CmsChannelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("channel")
public class ChannelController {

    @Autowired
    private CmsChannelService cmsChannelService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("channel","index");
    }

    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("channel","add");
    }

    @PostMapping("add.do")
    @ResponseBody
    public Result<String> doAdd(CmsChannelDto cmsChannelDto){
        cmsChannelService.save(cmsChannelDto);
        return Result.success();
    }


}
