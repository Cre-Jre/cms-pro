package com.cms.service.impl;

import com.cms.dao.entity.CmsPermissionEntity;
import com.cms.dao.mapper.CmsPermissionMapper;
import com.cms.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.beans.binding.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class CmsPermissionServiceImplTest {


    @Test
    public void add(){
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
        log.info("success");
    }

    @Test
    public void edit(){
        List<CmsPermissionDto> cmsPermissionDtos = buildData();
        //存放所有数据 方便存取
        Map<Integer, CmsPermissionDto> permissionMap = Maps.newHashMap();
        //只存放parentId = 0的数据
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //在修改的时候id为3的要排除掉
        Integer excludeId = 3;
        //循环数据 进行处理
        cmsPermissionDtos.forEach(x->{
            Integer id = x.getId();
            if(id.compareTo(excludeId)==0){
                return;
            }
            permissionMap.put(id,x);
            //获取当前dto的父类id
            Integer parentId = x.getParentId();
            //判断是否是顶级菜单
            if(parentId==0){
                permissionList.add(x);
            }else{
                CmsPermissionDto cmsPermissionDto = permissionMap.get(parentId);
                if(Objects.isNull(cmsPermissionDto) && parentId.compareTo(excludeId)==0){
                    return;
                }
                List<CmsPermissionDto> children = cmsPermissionDto.getChildren();
                if(CollectionUtils.isEmpty(children)){
                    children = Lists.newArrayList();
                }
                children.add(x);
                cmsPermissionDto.setChildren(children);
            }
        });
        log.info("success");
    }

    public List<CmsPermissionDto> buildData(){
        List<CmsPermissionDto> permissionList = Lists.newArrayList();
        //4条数据
        CmsPermissionDto cmsPermissionDto = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto2 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto3 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto4 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto5 = new CmsPermissionDto();


        cmsPermissionDto.setId(1);
        cmsPermissionDto2.setId(2);
        cmsPermissionDto3.setId(3);
        cmsPermissionDto4.setId(4);
        cmsPermissionDto5.setId(5);

        cmsPermissionDto.setName("测试");
        cmsPermissionDto2.setName("测试2");
        cmsPermissionDto3.setName("测试3");
        cmsPermissionDto4.setName("测试4");
        cmsPermissionDto5.setName("测试5");

        cmsPermissionDto.setParentId(0);
        cmsPermissionDto2.setParentId(1);
        cmsPermissionDto3.setParentId(2);
        cmsPermissionDto4.setParentId(3);
        cmsPermissionDto5.setParentId(0);

        permissionList.add(cmsPermissionDto);
        permissionList.add(cmsPermissionDto2);
        permissionList.add(cmsPermissionDto3);
        permissionList.add(cmsPermissionDto4);
        permissionList.add(cmsPermissionDto5);
        return permissionList;
    }

}