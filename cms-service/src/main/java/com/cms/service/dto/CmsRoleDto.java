package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsRoleDto extends BaseDto<Integer> {
    private String name;
    private Integer priority;
    private Boolean all;
    private Boolean status;
}