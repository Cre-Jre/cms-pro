package com.cms.contex.utils;

public class UtilsTemplate {

    private UtilsTemplate() {
    }

    /**
     * 后台模板方法
     * @param template
     * @return
     */
    public static String adminTemplate(String template){
        return "admin/"+template;
    }

    /**
     * 后台模板
     * @param dir   目录
     * @param template  名称
     * @return
     */
    public static String adminTemplate(String dir,String template){
        return "admin/"+dir+"/"+template;
    }
}
