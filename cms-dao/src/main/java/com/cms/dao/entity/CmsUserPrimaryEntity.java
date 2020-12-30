package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CmsUserPrimaryEntity extends BaseEntity<Integer> {
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
