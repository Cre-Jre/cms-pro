package com.cms.service.api;

public interface CommonService {

    /**
     * 验证 验证码
     * @param captcha
     * @return
     */
    String verifyImageCaptcha(String captcha);
}