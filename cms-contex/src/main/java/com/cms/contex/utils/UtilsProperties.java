package com.cms.contex.utils;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 个人微信: 15254124776
 * 个人qq:  2301887641
 */
@Component
public class UtilsProperties implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }

    /**
     * 获取属性值
     * @param key   属性
     * @return
     */
    public String getPropertiesValue(String key){
        return stringValueResolver.resolveStringValue("${"+key+"}");
    }
}
