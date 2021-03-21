package com.cms.core.exception;

import com.cms.core.enums.RestCodeEnum;
import com.cms.core.foundation.BaseEnum;
import com.cms.core.foundation.BaseException;

public class BusinessException extends BaseException {

    /**
     * 带有错误信息的构造函数
     * @param msg           错误信息
     */
    public BusinessException(String msg) {
        super(RestCodeEnum.ERROR.getOrdinal(),msg);
    }

    /**
     * 带有错误码和错误消息的构造函数
     *
     * @param enums 枚举类
     * @param msg   错误信息
     */
    public BusinessException(BaseEnum enums, String msg) {
        super(enums.getOrdinal(), msg);
    }
}
