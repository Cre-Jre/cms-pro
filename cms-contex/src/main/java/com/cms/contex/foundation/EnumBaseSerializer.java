package com.cms.contex.foundation;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.cms.core.foundation.BaseEnum;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * fastJson自定义枚举序列化器
 */
public class EnumBaseSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int i) throws IOException {
        SerializeWriter out = jsonSerializer.out;
        if(object instanceof BaseEnum){
            out.writeString(((BaseEnum) object).getLabel());
        }else{
            out.writeEnum((Enum<?>)object);
        }
    }

}
