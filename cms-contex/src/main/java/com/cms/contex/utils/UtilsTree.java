package com.cms.contex.utils;

import com.cms.core.foundation.TreeDto;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class UtilsTree {

    /**
     * 递归给树形结构添加checkArr复选框
     * @param permissionList          数据
     * @param <PK>                    主键
     * @param <T>                     实体
     */
    public static<PK extends Serializable,T extends TreeDto<T,PK>> void recursion(List<T> permissionList){
        permissionList.forEach(x->{
            x.setCheckArr(Collections.singletonList(Collections.singletonMap("checked","0")));
            if(!CollectionUtils.isEmpty(x.getChildren())){
                recursion(x.getChildren());
            }
        });
    }
}
