package com.cms.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoLog {
    /**
     * 日志的内容
     * @return
     */
    String content();
}
