package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentTopicEntity extends BaseEntity<Integer> {
    private Integer contentId;
    private Integer topicId;
}
