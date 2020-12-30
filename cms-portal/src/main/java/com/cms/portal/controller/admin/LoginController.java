package com.cms.portal.controller.admin;

import com.cms.contex.constant.ConstantsPool;
import com.cms.contex.utils.UtilsHttp;
import com.cms.contex.utils.UtilsShiro;
import com.cms.service.api.CommonService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Controller
@Slf4j
public class LoginController {


    @Autowired
    private Producer captchaProducer;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @GetMapping("login.do")
    public String toLogin() {
        Subject subject = UtilsShiro.getSubject();
        if (subject.isAuthenticated()){
            return "redirect:index.do";
        }
        return "/admin/login";
    }

    @GetMapping("captcha.do")
    public void doCaptcha(HttpServletResponse response) {
        String text = captchaProducer.createText();
        // sessionId + "image_captcha"
        redisTemplate.opsForValue().set(UtilsShiro.getSession().getId() + ConstantsPool.IMAGE_CAPTCHA_SUFFIX, text, 60, TimeUnit.SECONDS);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //推荐大家自己来关闭response流
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(captchaProducer.createImage(text), "jpg", outputStream);
        } catch (IOException e) {
            log.error("验证码生成失败");
        }
    }


}

