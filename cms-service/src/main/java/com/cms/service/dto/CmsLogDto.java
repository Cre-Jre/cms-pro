package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CmsLogDto extends BaseDto<Integer> {
    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;

    public static CmsLogDto of(Integer userId, String username,String loginIp,String url,String content){
        CmsLogDto cmsLogDto = new CmsLogDto();
        cmsLogDto.setContent(content);
        cmsLogDto.setUrl(url);
        cmsLogDto.setLoginIp(loginIp);
        cmsLogDto.setUserId(userId);
        cmsLogDto.setUsername(username);
//        cmsLogDto.setCreateTime(LocalDateTime.now());
        return  cmsLogDto;
    }
}
