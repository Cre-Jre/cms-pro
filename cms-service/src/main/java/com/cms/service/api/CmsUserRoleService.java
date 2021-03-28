package com.cms.service.api;

import com.cms.core.foundation.BaseService;
import com.cms.service.dto.CmsUserRoleDto;

import java.util.List;

public interface CmsUserRoleService extends BaseService<CmsUserRoleDto,Integer> {

    /**
     * 通过用户id查询用户权限
     * @param userId        用户id
     * @return              用户权限
     */
    List<String> selectPermissionsByUserId(Integer userId);
}
