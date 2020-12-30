package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CmsUserDto extends BaseDto<Integer> {
    private String username;
    private UserStatusEnum status;
    private Boolean admin;
    private String lastLoginIp;
    private String sessionId;
    /**
     * 超级管理员
     */
    private Boolean administrator;
}
