package com.cms.portal.controller.admin;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.core.annotation.DoLog;
import com.cms.service.api.CmsRoleService;
import com.cms.service.api.CmsUserService;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CmsRoleService cmsRoleService;
    @Autowired
    private CmsUserService cmsUserService;

    @RequiresPermissions("admin:index")
    @GetMapping("index.do")
    public String toIndex(){
        return UtilsTemplate.adminTemplate("admin","index");
    }

    @RequiresPermissions("admin:add")
    @GetMapping("add.do")
    public String toAdd(Model model){
        model.addAttribute("roles",cmsRoleService.getList());
        return UtilsTemplate.adminTemplate("admin","add");
    }

    @RequiresPermissions("admin:add")
    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加管理员")
    public Result<String> doAdd(CmsUserDto cmsUserDto){
        CmsUserDto cmsUserByUsername = cmsUserService.selectByUsername(cmsUserDto.getUsername());
        if(Objects.nonNull(cmsUserByUsername)){
            return Result.failed("当前用户已经存在");
        }
        CmsUserDto cmsUserByEmail = cmsUserService.selectByEmail(cmsUserDto.getEmail());
        if(Objects.nonNull(cmsUserByEmail)){
            return Result.failed("当前邮箱已经存在");
        }
        cmsUserService.save(cmsUserDto);
        return Result.success();
    }

    @PostMapping("delete.do")
    @ResponseBody
    public Result<String> doDelete(Integer id){
        cmsUserService.deleteById(id);
        return Result.success();
    }

    @GetMapping("edit.do")
    public String toEdit(Integer id,Model model){
        model.addAttribute("data",cmsUserService.getById(id));
        model.addAttribute("roles",cmsRoleService.getList());
        return UtilsTemplate.adminTemplate("admin","edit");
    }

    @PostMapping("edit.do")
    @ResponseBody
    public Result<String> doEdit(CmsUserDto cmsUserDto){
        CmsUserDto cmsUserByUsername = cmsUserService.selectByUsername(cmsUserDto.getUsername());
        if(Objects.nonNull(cmsUserByUsername) && Integer.compare(cmsUserByUsername.getId(),cmsUserDto.getId())!=0){
            return Result.failed("当前用户已经存在");
        }
        CmsUserDto cmsUserByEmail = cmsUserService.selectByEmail(cmsUserDto.getEmail());
        if(Objects.nonNull(cmsUserByEmail) && Integer.compare(cmsUserByEmail.getId(),cmsUserDto.getId())!=0){
            return Result.failed("当前邮箱已经存在");
        }
        cmsUserService.update(cmsUserDto);
        return Result.success();
    }

    @FastJsonView(exclude={
            @FastJsonFilter(clazz=CmsUserDto.class,props={"password","salt"})
    })
    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsUserDto cmsUserDto){
        return Result.success(cmsUserService.getPage(cmsUserDto));
    }

}
