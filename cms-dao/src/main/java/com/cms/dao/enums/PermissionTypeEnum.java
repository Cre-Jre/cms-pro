package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 * 枚举类
 */
@Getter
public enum PermissionTypeEnum implements BaseEnum {
    BUTTON(0,"按钮"),
    MENU(1,"菜单");

    private int ordinal;
    private String label;

    PermissionTypeEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
