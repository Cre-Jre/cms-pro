package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentTxtDto extends BaseDto<Integer> {
    private Integer contentId;
    private String content;
}
