package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.contex.utils.UtilsTree;
import com.cms.core.annotation.DoLog;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.api.CmsRoleService;
import com.cms.service.dto.CmsPermissionDto;
import com.cms.service.dto.CmsRoleDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private CmsRoleService cmsRoleService;
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

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加角色")
    public Result doAdd(CmsRoleDto cmsRoleDto){
        cmsRoleService.save(cmsRoleDto);
        return Result.success();
    }

    @PostMapping("permission.do")
    @ResponseBody
    public Result doPermission(){
        List<CmsPermissionDto> permissionList =cmsPermissionService.getTree(null);
        UtilsTree.recursion(permissionList);
        return Result.success((ArrayList) permissionList);
    }
}
