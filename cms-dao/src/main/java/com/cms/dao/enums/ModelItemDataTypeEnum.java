package com.cms.dao.enums;

import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

@Getter
public enum ModelItemDataTypeEnum implements BaseEnum {

    /**
     * 模型项的字段数据类型
     */
    STRING(1,"字符串文本"),
    INTEGER(2,"整型文本"),
    FLOAT(3,"浮点型"),
    TEXT_AREA(4,"文本区"),
    DATE(5,"日期"),
    SELECT(6,"下拉列表"),
    CHECKBOX(7,"复选框"),
    RADIO(8,"单选框"),
    ATTACHMENT(9,"附件"),
    PICTURE(10,"图片");

    private int ordinal;
    private String label;

    ModelItemDataTypeEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
