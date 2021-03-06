package com.cms.dao.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.cms.contex.foundation.EnumBaseSerializer;
import com.cms.core.foundation.BaseEnum;
import lombok.Getter;

/**
 *任务类型
 */
@Getter
@JSONType(serializeEnumAsJavaBean = true,serializer= EnumBaseSerializer.class)
public enum TaskStaticTypeEnum implements BaseEnum {
    INDEX(0,"首页静态化"),
    CHANNEL(1,"栏目静态化"),
    CONTENT(2,"内容静态化");

    private int ordinal;
    private String label;

    TaskStaticTypeEnum(int ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
