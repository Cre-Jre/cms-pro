package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.dto.CmsPermissionDto;
import com.cms.service.dto.CmsRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("role","index");
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsRoleDto cmsRoleDto){
        return Result.success();
    }

    @GetMapping("add.do")
    public String toAdd(){
        return UtilsTemplate.adminTemplate("role","add");
    }

    @PostMapping("permission.do")
    @ResponseBody
    public Result doPermission(){
        ArrayList<CmsPermissionDto> list = (ArrayList)cmsPermissionService.getList(null);
        return Result.success(list);
    }
}
