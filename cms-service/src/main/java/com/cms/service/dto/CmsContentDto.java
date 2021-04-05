package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentDto extends BaseDto<Integer> {
    private String title;
    private String content;
}
