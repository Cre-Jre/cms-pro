package com.cms.dao.mapper;

import com.cms.core.foundation.BaseMapper;
import com.cms.dao.entity.CmsPermissionEntity;

import java.util.List;

public interface CmsPermissionMapper extends BaseMapper<CmsPermissionEntity,Integer> {
    /**
     * 根据父类id查找
     * @param parentId
     * @return
     */
    List<CmsPermissionEntity> selectByParentId(Integer parentId);
}
