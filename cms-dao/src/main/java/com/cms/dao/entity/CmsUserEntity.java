package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
    private String username;
    private Integer status;
    private Boolean admin;
    private String lastLoginIp;
    private String sessionId;
    /**
     * 超级管理员
     */
    private Boolean administrator;

}

