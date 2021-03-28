package com.cms.contex.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class UtilsShiro {

    /**
     * 通过shiro获取session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取shiro的subject
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 生成salt值
     * @return
     */
    public static String generateSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

}
