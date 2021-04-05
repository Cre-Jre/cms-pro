package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsChannelEntity extends BaseEntity<Integer> {
    private String name;
    private String keyword;
    private String description;
    private String path;
    private Integer modelId;
    private Integer priority;
    private Integer parentId;
    private Boolean delete;
}
