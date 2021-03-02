package com.cms.portal.interceptor;

import com.cms.contex.utils.UtilsHttp;
import com.cms.contex.utils.UtilsShiro;
import com.cms.core.annotation.DoLog;
import com.cms.service.api.CmsLogService;
import com.cms.service.dto.CmsLogDto;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CmsLogService cmsLogService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handler1 = (HandlerMethod) handler;
        DoLog doLog = handler1.getMethodAnnotation(DoLog.class);
        Optional.ofNullable(doLog).ifPresent(x->{
            String requestURI = request.getRequestURI();
            String ip = UtilsHttp.getRemoteAddress();
            String content = doLog.content();
            threadPoolTaskExecutor.execute(()->{
                Subject subject = UtilsShiro.getSubject();
                CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();
                cmsLogService.save(CmsLogDto.of(cmsUserDto.getId(),cmsUserDto.getUsername(),ip,requestURI,content));
            });
        });
        super.postHandle(request, response, handler, modelAndView);
    }
}
