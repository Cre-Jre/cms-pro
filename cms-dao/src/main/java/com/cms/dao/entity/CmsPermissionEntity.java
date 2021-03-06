package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsPermissionEntity extends BaseEntity<Integer> {
    private Integer parentId;
    private Boolean imenu;
    private String icon;
    private String name;
    private String url;
    private String action;
    private Integer priority;
}
