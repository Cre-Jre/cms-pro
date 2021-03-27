package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.contex.utils.UtilsTree;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.api.CmsRolePermissionService;
import com.cms.service.api.CmsRoleService;
import com.cms.service.dto.CmsPermissionDto;
import com.cms.service.dto.CmsRoleDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@Validated
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private CmsRoleService cmsRoleService;
    @Autowired
    private CmsPermissionService cmsPermissionService;
    @Autowired
    private CmsRolePermissionService cmsRolePermissionService;

    @GetMapping("index.do")
    public String toIndex() {
        return UtilsTemplate.adminTemplate("role", "index");
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsRoleDto cmsRoleDto) {
        return Result.success(cmsRoleService.getPage(cmsRoleDto));
    }

    @GetMapping("add.do")
    public String toAdd() {
        return UtilsTemplate.adminTemplate("role", "add");
    }

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加角色")
    @DoValid
    public Result doAdd(@Valid CmsRoleDto cmsRoleDto, BindingResult result) {
        cmsRoleService.save(cmsRoleDto);
        return Result.success();
    }

    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "请输入id") Integer id, Model model) {
        model.addAttribute("data", cmsRoleService.getById(id));
        return UtilsTemplate.adminTemplate("role", "edit");
    }

    @PostMapping("permission.do")
    @ResponseBody
    public Result doPermission(Integer roleId) {
        List<CmsPermissionDto> permissionList = cmsPermissionService.getTree(null);
        List<Integer> permissionIds = null;
        if(Objects.nonNull(roleId)){
            permissionIds = cmsRolePermissionService.getPermissionIdByRoleId(roleId);
        }
        UtilsTree.recursion(permissionList,permissionIds);
        return Result.success((ArrayList) permissionList);
    }
}
