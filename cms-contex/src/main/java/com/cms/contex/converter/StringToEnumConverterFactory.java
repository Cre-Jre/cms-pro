package com.cms.contex.converter;

import com.cms.core.foundation.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Objects;
import java.util.WeakHashMap;

public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    private static final WeakHashMap<String,Converter> CACHE_MAP = new WeakHashMap<>();

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        String simpleName = targetType.getSimpleName();
        Converter converter = CACHE_MAP.get(simpleName);
        if(Objects.isNull(converter)){
            converter = new StringToEnumConverter(targetType);
            CACHE_MAP.put(simpleName,converter);
        }
        return converter;
    }

    public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {

        private Class<T> clazz;

        public StringToEnumConverter(Class<T> enumType) {
            clazz = enumType;
        }

        @Override
        public T convert(String str) {
            for (T enumType : clazz.getEnumConstants()) {
                if (Objects.equals(enumType.getOrdinal(), Integer.parseInt(str))) {
                    return enumType;
                }
            }
            return null;
        }
    }
}
