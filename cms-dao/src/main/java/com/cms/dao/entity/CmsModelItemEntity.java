package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsModelItemEntity extends BaseEntity<Integer> {
    private Integer modelId;
    private String field;
    private String label;
    private Integer dataType;
    private Boolean channelModel;
    private Integer required;
    private Integer delete;
    private Integer single;
}
