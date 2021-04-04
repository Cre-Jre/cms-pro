package com.cms.contex.utils;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 * 字符串操作相关工具
 */
public class UtilsString {

    /**
     * uuid基于时间
     * @return          字符串
     */
    public static String uuid(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString();
    }
}
