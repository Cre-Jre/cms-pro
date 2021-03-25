package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsRolePermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsRolePermissionMapper extends BaseMapper<CmsRolePermissionEntity,Integer> {
    /**
     * 批量插入
     * @param permissionList      权限集合
     * @param roleId              角色id
     */
    void batchInsert(@Param("permissionList") List<Integer> permissionList, @Param("roleId")Integer roleId);

}
