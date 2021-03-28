package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsUserRoleDto extends BaseDto<Integer> {
    private Integer roleId;
    private Integer userId;
}
