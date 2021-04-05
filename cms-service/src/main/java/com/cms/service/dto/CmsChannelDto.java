package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsChannelDto extends BaseDto<Integer> {
    private String name;
    private String keyword;
    private String description;
    private String path;
    private Integer modelId;
    private Integer priority;
    private Integer parentId;
    private Boolean delete;
}
