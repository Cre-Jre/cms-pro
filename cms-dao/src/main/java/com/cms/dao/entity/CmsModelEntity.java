package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsModelEntity extends BaseEntity<Integer> {
    private String name;
    private String tplChannelPrefix;
    private String tplContentPrefix;
    private Integer priority;
    private Boolean hasContent;
    private Boolean delete;
}
