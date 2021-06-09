package com.cms.portal.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
public class CmsUserFilter extends UserFilter {
    private String adminLoginUrl;
    private String adminPrefix;

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse response) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        this.saveRequest(request);
        String requestURI = request.getRequestURI();
        WebUtils.issueRedirect(request, response, (requestURI.startsWith(adminPrefix)) ? adminLoginUrl : this.getLoginUrl());
        return false;
    }
}
