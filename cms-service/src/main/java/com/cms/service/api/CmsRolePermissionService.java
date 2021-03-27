package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsRolePermissionDto;

import java.util.List;

public interface CmsRolePermissionService extends BaseService<CmsRolePermissionDto,Integer> {
    /**
     * 根据角色id 查询权限
     * @param roleId      角色id
     * @return
     */
    List<Integer> getPermissionIdByRoleId(Integer roleId);

}
