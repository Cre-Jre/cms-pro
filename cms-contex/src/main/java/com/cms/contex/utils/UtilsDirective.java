package com.cms.contex.utils;

import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

@Slf4j
public class UtilsDirective {

    public static final DefaultObjectWrapper DEFAULT_OBJECT_WRAPPER = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23).build();

    /**
     * 获取int参数值
     * @param name      key
     * @param map       map
     * @return
     */
    public static int getInt(String name, Map map) throws TemplateModelException {
        TemplateModel templateModel = (TemplateModel) map.get(name);
        if(Objects.isNull(templateModel)){
            return 0;
        }
        //假如是字符串
        if(templateModel instanceof TemplateScalarModel){
            TemplateScalarModel templateScalarModel = (TemplateScalarModel) templateModel;
            return Integer.parseInt(templateScalarModel.getAsString());
        }
        //数字类型
        if(templateModel instanceof TemplateNumberModel){
            TemplateNumberModel templateNumberModel = (TemplateNumberModel) templateModel;
            return templateNumberModel.getAsNumber().intValue();
        }
        return 0;
    }



}
