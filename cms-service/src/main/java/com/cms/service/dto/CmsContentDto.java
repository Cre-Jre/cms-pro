package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.entity.CmsChannelEntity;
import com.cms.dao.entity.CmsContentTxtEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentDto extends BaseDto<Integer> {
    private String title;
    private Integer channelId;
    private Integer userId;
    private Integer modelId;
    private String author;
    private String description;
    private String titleImg;
    private String content;
    private Integer topicId;

    private String url;
    private CmsChannelEntity cmsChannel;
    private CmsContentTxtEntity contentTxt;
}
