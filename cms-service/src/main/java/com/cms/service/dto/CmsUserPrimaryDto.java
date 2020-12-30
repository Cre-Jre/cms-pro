package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsUserPrimaryDto extends BaseDto<Integer> {
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}

