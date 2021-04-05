package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

@Getter
public enum ModelItemRequiredEnum implements BaseEnum {
    /**
     * 是否必填
      */
    YES(1,"是"),
    NO(2,"否");

    private int ordinal;
    private String label;

    ModelItemRequiredEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
