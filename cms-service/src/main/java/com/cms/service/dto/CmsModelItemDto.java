package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.ModelItemDataTypeEnum;
import com.cms.dao.enums.ModelItemRequiredEnum;
import com.cms.dao.enums.ModelItemSingleEnum;
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
    private ModelItemRequiredEnum required;
    private Integer delete;
    private ModelItemSingleEnum single;

    public static CmsModelItemDto of(String field,ModelItemDataTypeEnum dataType,
                                     String label,ModelItemSingleEnum single,
                                     ModelItemRequiredEnum required){
        CmsModelItemDto cmsModelItemDto = new CmsModelItemDto();
        cmsModelItemDto.setField(field);
        cmsModelItemDto.setLabel(label);
        cmsModelItemDto.setSingle(single);
        cmsModelItemDto.setRequired(required);
        cmsModelItemDto.setDataType(dataType);
        return cmsModelItemDto;
    }
}
