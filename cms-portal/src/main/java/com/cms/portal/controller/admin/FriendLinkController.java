package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsFriendLinkService;
import com.cms.service.dto.CmsFriendLinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("friendLink")
public class FriendLinkController {

    @Autowired
    private CmsFriendLinkService cmsFriendLinkService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("friend","index");
    }

    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("friend","add");
    }

    @PostMapping("add.do")
    @ResponseBody
    public Result<String> doAdd(CmsFriendLinkDto cmsFriendLinkDto){
        cmsFriendLinkService.save(cmsFriendLinkDto);
        return Result.success();
    }
}
