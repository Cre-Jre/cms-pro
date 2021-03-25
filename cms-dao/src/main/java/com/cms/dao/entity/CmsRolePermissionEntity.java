package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsRolePermissionEntity extends BaseEntity<Integer> {
    private Integer roleId;
    private Integer permissionId;
}
