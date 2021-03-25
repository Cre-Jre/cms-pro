package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsTemplate;
import com.cms.service.api.CmsPermissionService;
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
        List<CmsPermissionDto> cmsPermissionDtos = cmsPermissionService.getList(null);
        //存放所有数据 方便存取
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        //只存放parentId = 0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //循环数据 进行处理
        cmsPermissionDtos.forEach(x -> {
            Integer id = x.getId();
            permissionMap.put(id, x);
            //获取当前dto的父类id
            Integer parentId = x.getParentId();
            //判断是否是顶级菜单
            if (parentId == 0) {
                permissionList.add(x);
            } else {
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if (CollectionUtils.isEmpty(children)) {
                    children = Lists.newArrayList();
                }
                children.add(x);
                children.sort(Comparator.comparing(CmsPermissionDto::getPriority));
                cmsPermissionDto.setChildren(children);
            }
        });
        permissionList.sort(Comparator.comparing(CmsPermissionDto::getPriority));
        recursion(permissionList);
        return Result.success((ArrayList) permissionList);
    }

    private void recursion(List<CmsPermissionDto> permissionList){
        permissionList.forEach(x->{
            x.setCheckArr(Collections.singletonList(Collections.singletonMap("checked","0")));
            if(!CollectionUtils.isEmpty(x.getChildren())){
                recursion(x.getChildren());
            }
        });
    }
}
