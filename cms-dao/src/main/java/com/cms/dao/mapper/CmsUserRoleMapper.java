package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsSiteEntity;
import com.cms.dao.entity.CmsUserRoleEntity;

import java.util.List;

public interface CmsUserRoleMapper extends BaseMapper<CmsUserRoleEntity,Integer> {
    /**
     * 通过用户id查询角色id
     * @param userId     用户id
     * @return
     */
    Integer selectByUserId(Integer userId);

    /**
     * 通过用户id查询用户权限
     * @param userId        用户id
     * @return              用户权限
     */
    List<String> selectPermissionsByUserId(Integer userId);
}
