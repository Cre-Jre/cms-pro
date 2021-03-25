package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.core.annotation.DoValid;
import com.cms.dao.enums.PermissionTypeEnum;
import com.cms.service.api.CmsPermissionService;
import com.cms.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@Validated
@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @GetMapping("index.do")
    public String toIndex() {
        return UtilsTemplate.adminTemplate("permission", "index");
    }

    @GetMapping("add.do")
    public String toAdd(Integer parentId, Model model) {
        if (Objects.nonNull(parentId)) {
            model.addAttribute("parentId", parentId);
        }
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "add");
    }

    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加权限")
    @DoValid
    public Result<String> doAdd(@Valid CmsPermissionDto cmsPermissionDto, BindingResult result) {
        cmsPermissionService.save(cmsPermissionDto);
        return Result.success();
    }

    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "请输入id") Integer id, Model model) {
        model.addAttribute("data", cmsPermissionService.getById(id));
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return UtilsTemplate.adminTemplate("permission", "edit");
    }

    @PostMapping("edit.do")
    @ResponseBody
    @DoLog(content = "修改权限")
    @DoValid
    public Result<String> doEdit(@Valid CmsPermissionDto cmsPermissionDto, BindingResult result){
        cmsPermissionService.update(cmsPermissionDto);
        return Result.success();
    }

    @PostMapping("delete.do")
    @ResponseBody
    public Result doDelete(@NotNull(message = "请传递id") Integer id){
        cmsPermissionService.deleteById(id);
        return Result.success();
    }

    @GetMapping("list.do")
    @ResponseBody
    public Result doList(CmsPermissionDto cmsPermissionDto) {
        return Result.success((ArrayList) cmsPermissionService.getList(cmsPermissionDto));
    }

    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(Integer excludeId) {
        return Result.success((ArrayList) cmsPermissionService.getTree(excludeId));
    }
}
