package com.cms.contex.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
public class UtilsServletContext implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 获取servlet容器中的真实路径 格式如下:
     * E:\project\java-cms\cms-portal\src\main\webapp\
     * D:\apache-tomcat-9.0.26\webapps\ROOT\
     *
     * @param path 传入的路径
     * @return 容器路径
     */
    public String getRealPath(String path) {
        return servletContext.getRealPath(StringUtils.isBlank(path) ? "/" : path);
    }
}
