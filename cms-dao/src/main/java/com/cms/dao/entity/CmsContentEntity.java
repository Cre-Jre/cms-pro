package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentEntity extends BaseEntity<Integer> {
    private String title;
    private String content;
}
