package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentTopicDto extends BaseDto<Integer> {
    private Integer contentId;
    private Integer topicId;
}
