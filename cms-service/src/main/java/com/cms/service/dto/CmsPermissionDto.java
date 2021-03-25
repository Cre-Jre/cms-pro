package com.cms.service.dto;

import com.cms.core.foundation.BaseDto;
import com.cms.core.foundation.TreeDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CmsPermissionDto extends TreeDto<CmsPermissionDto,Integer> {
    private Integer parentId;
    private Boolean menu;
    private String icon;
    @NotBlank(message = "请输入权限名称")
    private String name;
    private String url;
    private String action;
    @NotNull(message = "请输入排序")
    @Min(value = 0,message = "排序最小为0")
    @Max(value = 9999,message = "排序最大到9999")
    private Integer priority;
}
