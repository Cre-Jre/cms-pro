package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

@Getter
public enum StaticWebSuffixEnum implements BaseEnum {

    HTML(0,".html"),
    SHTML(1,".shtml");

    private int ordinal;
    private String label;

    StaticWebSuffixEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
