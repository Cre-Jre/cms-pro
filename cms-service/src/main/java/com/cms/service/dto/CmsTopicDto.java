package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsTopicDto extends BaseDto<Integer> {
    private String name;
    private String keywords;
    private String titleImg;
    private String contentImg;
    private String tplContent;
    private String description;
}
