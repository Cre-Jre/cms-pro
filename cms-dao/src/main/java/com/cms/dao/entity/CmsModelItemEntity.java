package com.cms.dao.entity;

import com.cms.core.foundation.BaseDto;
import com.cms.core.foundation.BaseEntity;
import com.cms.dao.enums.ModelItemDataTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsModelItemEntity extends BaseEntity<Integer> {
    private Integer modelId;
    private String field;
    private String label;
    private ModelItemDataTypeEnum dataType;
    private Boolean channelModel;
    private Integer required;
    private Integer delete;
    private Integer single;
}
