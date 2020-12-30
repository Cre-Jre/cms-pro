package com.cms.portal.freemarker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
public class CmsViewResolver extends FreeMarkerView {

    private static final String ADMIN_PATH = "/admin/cms/";


    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        //就认为是后台请求路径
        if (requestURI.contains(ADMIN_PATH)) {
            model.put("adminPath", contextPath + servletPath);
        }

        model.put("basePath", contextPath);
    }
}
