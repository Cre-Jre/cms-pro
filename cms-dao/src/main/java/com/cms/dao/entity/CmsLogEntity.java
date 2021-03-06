package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsLogEntity extends BaseEntity<Integer> {
    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;
}
