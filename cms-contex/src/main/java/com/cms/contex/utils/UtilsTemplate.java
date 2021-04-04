package com.cms.contex.utils;

public class UtilsTemplate {

    private UtilsTemplate() {
    }

    /**
     * 后台模板方法
     *
     * @param template 模板
     * @return string
     */
    public static String adminTemplate(String template) {
        return "admin/" + template;
    }

    /**
     * 后台模板方法
     * @param dir           模板目录
     * @param template      模板名称
     * @return              string
     */
    public static String adminTemplate(String dir, String template) {
        return "admin/" + dir + "/" + template;
    }

    /**
     * 前台模板方法
     * @param template      模板名称
     * @return              string
     */
    public static String frontTemplate(String template){
        return "front/default/"+template;
    }

    /**
     * 前台模板方法
     * @param dir           目录
     * @param template      模板名称
     * @return              string
     */
    public static String frontTemplate(String dir,String template){
        return "front/default/"+dir+"/"+template;
    }
}
