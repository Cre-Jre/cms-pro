package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsTopicEntity extends BaseEntity<Integer> {
    private String name;
    private String keywords;
    private String titleImg;
    private String contentImg;
    private String tplContent;
    private String description;
}
