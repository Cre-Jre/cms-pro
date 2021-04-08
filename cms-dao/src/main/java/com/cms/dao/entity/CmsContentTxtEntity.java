package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentTxtEntity extends BaseEntity<Integer> {
    private Integer contentId;
    private String content;
}
