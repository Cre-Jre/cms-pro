package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsFriendLinkEntity extends BaseEntity<Integer> {
    private String name;
    private String url;
    private Integer priority;
}
