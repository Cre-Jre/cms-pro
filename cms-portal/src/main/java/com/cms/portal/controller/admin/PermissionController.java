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
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private CmsPermissionService cmsPermissionService;

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
    @ResponseBody
    @DoLog(content = "添加权限")
    @DoValid
    public Result<String> doAdd(@Valid CmsPermissionDto cmsPermissionDto, BindingResult result){
        cmsPermissionService.save(cmsPermissionDto);
        return Result.success();
    }

    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(){
        List<CmsPermissionDto> cmsPermissionDtos = buildData();
        //存放所有数据 方便存取
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        //只存放parentId = 0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //循环数据 进行处理
        cmsPermissionDtos.forEach(x->{
            Integer id = x.getId();
            permissionMap.put(id,x);
            //获取当前dto的父类id
            Integer parentId = x.getParentId();
            //判断是否是顶级菜单
            if(parentId==0){
                permissionList.add(x);
            }else{
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if(CollectionUtils.isEmpty(children)){
                    children = Lists.newArrayList();
                }
                children.add(x);
                cmsPermissionDto.setChildren(children);
            }
        });
        return Result.success((ArrayList)permissionList);
    }

    public List<CmsPermissionDto> buildData(){
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //4条数据
        CmsPermissionDto cmsPermissionDto = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto2 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto3 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto4 = new CmsPermissionDto();

        cmsPermissionDto.setId(1);
        cmsPermissionDto2.setId(2);
        cmsPermissionDto3.setId(3);
        cmsPermissionDto4.setId(4);

        cmsPermissionDto.setName("测试");
        cmsPermissionDto2.setName("测试2");
        cmsPermissionDto3.setName("测试3");
        cmsPermissionDto4.setName("测试4");

        cmsPermissionDto.setParentId(0);
        cmsPermissionDto2.setParentId(1);
        cmsPermissionDto3.setParentId(2);
        cmsPermissionDto4.setParentId(3);

        permissionList.add(cmsPermissionDto);
        permissionList.add(cmsPermissionDto2);
        permissionList.add(cmsPermissionDto3);
        permissionList.add(cmsPermissionDto4);
        return permissionList;
    }

}
