package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsTaskEntity extends BaseEntity<Integer> {
    private String name;
    private String code;
    private Integer type;
    private Integer taskExecutionType;
    private Integer dayOfMonth;
    private Integer dayOfWeek;
    private Integer hour;
    private Integer minute;
    private Integer intervalHour;
    private Integer intervalMinute;
    private String cronExpression;
    private Boolean enable;
    private Integer intervalUnit;
    private String remark;
}
