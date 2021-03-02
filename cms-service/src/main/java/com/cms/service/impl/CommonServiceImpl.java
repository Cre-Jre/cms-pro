package com.cms.service.impl;

import com.cms.contex.utils.UtilsShiro;
import com.cms.service.api.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    private static final String IMAGE_CAPTCHA_SUFFIX = "image_captcha";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public String verifyImageCaptcha(String captcha) {
        String redisCaptcha = redisTemplate.opsForValue().get(UtilsShiro.getSession().getId() + IMAGE_CAPTCHA_SUFFIX);
        if(Objects.isNull(redisCaptcha)){
            return "验证码已失效请重新输入!";
        }
        if(!StringUtils.equals(captcha,redisCaptcha)){
            return "验证码输入错误";
        }
        return null;
    }

}

