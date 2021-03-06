package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsPermissionDto extends BaseDto<Integer> {
    private Integer parentId;
    private Boolean imenu;
    private String icon;
    private String name;
    private String url;
    private String action;
    private Integer priority;
}
