package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsFriendLinkDto extends BaseDto<Integer> {
    private String name;
    private String url;
    private Integer priority;
}
