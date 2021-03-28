package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
    private String username;
    private Boolean status;
    private Boolean admin;
    private String password;
    private String salt;
    private String email;
    private Integer roleId;
    private String roleName;
    private LocalDateTime registerTime;
}
