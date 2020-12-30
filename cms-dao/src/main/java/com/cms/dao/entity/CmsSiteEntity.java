package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsSiteEntity extends BaseEntity<Integer> {
    private String siteName;
    private String keywords;
    private String description;
}
