package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsModelDto extends BaseDto<Integer> {
    private String name;
    private String tplChannelPrefix;
    private String tplContentPrefix;
    private Integer priority;
    private Boolean hasContent;
    private Boolean delete;
}
