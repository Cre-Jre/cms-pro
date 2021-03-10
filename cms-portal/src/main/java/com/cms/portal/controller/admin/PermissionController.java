package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.dao.enums.PermissionTypeEnum;
import com.cms.service.dto.CmsPermissionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("permission","index");
    }

    @GetMapping("add.do")
    public String toAdd(Model model){
        PermissionTypeEnum[] values = PermissionTypeEnum.values();
        model.addAttribute("permissionType",values);
        return UtilsTemplate.adminTemplate("permission","add");
    }

    @PostMapping("add.do")
    public Result<String> doAdd(CmsPermissionDto cmsPermissionDto){
        return Result.success();
    }

}
