package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.ModelItemDataTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsModelItemDto extends BaseDto<Integer> {
    private Integer modelId;
    private String field;
    private String label;
    private ModelItemDataTypeEnum dataType;
    private Boolean channelModel;
    private Integer required;
    private Integer delete;
    private Integer single;
}
