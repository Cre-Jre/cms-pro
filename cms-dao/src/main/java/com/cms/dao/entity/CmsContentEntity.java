package com.cms.dao.entity;

import com.cms.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsContentEntity extends BaseEntity<Integer> {
    private String title;
    private Integer channelId;
    private Integer userId;
    private Integer modelId;
    private String author;
    private String description;
    private String titleImg;

    private CmsChannelEntity cmsChannel;
    private CmsContentTxtEntity contentTxt;
}
