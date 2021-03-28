package com.cms.portal.security.filter;

import com.alibaba.fastjson.JSON;
import com.cms.contex.constant.ConstantsPool;
import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsHttp;
import com.cms.contex.utils.UtilsShiro;
import com.cms.service.api.CmsLogService;
import com.cms.service.api.CmsUserService;
import com.cms.service.api.CommonService;
import com.cms.service.dto.CmsLogDto;
import com.cms.service.dto.CmsUserDto;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Objects;

public class CmsAuthenticationFilter extends FormAuthenticationFilter {

    private static final String ADMIN_LOGIN = "/admin/cms/login.do";

    @Autowired
    private CmsLogService cmsLogService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CmsUserService cmsUserService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request) ||
                this.pathsMatch(ADMIN_LOGIN, request);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String captcha = commonService.verifyImageCaptcha(WebUtils.getCleanParam(request, "captcha"));
        //TODO 跳过校验验证码
        if(1>2 && Objects.nonNull(captcha)){
            writer.write(JSON.toJSONString(Result.failed(captcha)));
            writer.close();
            return false;
        }
        Subject subject = UtilsShiro.getSubject();
        AuthenticationToken token = this.createToken(request, response);
        try{
            subject.login(token);
            onLoginSuccess(token,subject,request,response);
            writer.write(JSON.toJSONString(Result.success("登录成功")));
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            writer.write(JSON.toJSONString(Result.failed("用户名或密码错误,请重新输入!")));
        }catch (DisabledAccountException e){
            writer.write(JSON.toJSONString(Result.failed(e.getMessage())));
        }catch (Exception e){
            //用户有可能已经登录 其他错误
            writer.write(JSON.toJSONString((subject.isAuthenticated()?Result.success("登录成功"):Result.failed(ConstantsPool.EXCEPTION_NETWORK_ERROR))));
        }
        writer.close();
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        String ip = UtilsHttp.getRemoteAddress();
        threadPoolTaskExecutor.execute(()->{
            CmsUserDto cmsUserDto = (CmsUserDto) subject.getPrincipal();
            cmsUserService.updateLoginCount(cmsUserDto.getId());
            cmsLogService.save(CmsLogDto.of(cmsUserDto.getId(),cmsUserDto.getUsername(),ip,url,"用户后台系统登录"));
        });
        return false;
    }
}
