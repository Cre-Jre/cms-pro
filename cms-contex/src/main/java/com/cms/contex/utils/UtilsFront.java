package com.cms.contex.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 前台工具类
 */
@Slf4j
public class UtilsFront {

    /**
     * 获取当前分页 页码数
     * @return
     */
    public static UtilsHttp.FrontUrlInfo getUrlInfo(){
        HttpServletRequest request = UtilsHttp.getRequest();
        String requestURI = request.getRequestURI();
        int pageCurrent = 1;
        /**
         * url格式: /topic_2.jspx  topic_3.jspx
         * 获取"_"最后出现的位置
         */
        int underlineLastIndex = StringUtils.lastIndexOf(requestURI, "_");
        int ditLastIndex = StringUtils.lastIndexOf(requestURI, ".");
        int slashIndex = StringUtils.lastIndexOf(requestURI, "/");
        //url前缀
        String prefix="",suffix="";
        if(ditLastIndex!=-1){
            prefix = StringUtils.substring(requestURI,slashIndex+1,ditLastIndex);
        }
        //然后再根据_进行截取,就可以了
        if(underlineLastIndex!=-1){
            prefix = StringUtils.substring(requestURI,slashIndex+1,ditLastIndex);
        }
        //截取后缀
        suffix = StringUtils.substring(requestURI,ditLastIndex,requestURI.length());
        //当前页码
        if(underlineLastIndex!=-1 && ditLastIndex!=-1){
            String underlineLastIndexValue = StringUtils.substring(requestURI, underlineLastIndex + 1, ditLastIndex);
            log.info("获取到的当前分页=[{}]",underlineLastIndexValue);
            pageCurrent = Integer.parseInt(underlineLastIndexValue);
        }
        return new UtilsHttp.FrontUrlInfo(pageCurrent,prefix,suffix);
    }

    /**
     * 页面放入分页信息
     * @param dataMap
     */
    public static void assignPageInfo(Map<String,Object> dataMap){
        UtilsHttp.FrontUrlInfo urlInfo = getUrlInfo();
        dataMap.put("prefix",urlInfo.getPrefix());
        dataMap.put("suffix",urlInfo.getSuffix());
        dataMap.put("pageCurrent",urlInfo.getPageCurrent());
    }

}
