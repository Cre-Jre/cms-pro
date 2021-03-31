package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.dao.enums.StaticWebSuffixEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class CmsSiteDto extends BaseDto<Integer> {
    @NotBlank(message = "请输入站点名称")
    private String siteName;
    @NotBlank(message = "请输入站点关键字")
    private String keywords;
    @NotBlank(message = "请输入站点描述")
    private String description;
    private StaticWebSuffixEnum staticSuffix;
    private String staticDir;
    private Boolean staticIndex;
}
