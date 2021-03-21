package com.cms.contex.advice;

import com.cms.contex.constant.ConstantsPool;
import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsHttp;
import com.cms.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class UnificationExceptionHandler {

    /**
     * 方法参数异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<String> constraintViolationExceptionHandler(ConstraintViolationException exception){
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        for(ConstraintViolation<?> item:constraintViolations){
            log.info(item.getPropertyPath().toString()+item.getMessage());
            return UtilsHttp.responseExceptionHandler(item.getMessage(),"/error.do");
        }
        return UtilsHttp.responseExceptionHandler(ConstantsPool.EXCEPTION_NETWORK_ERROR,"/error.do");
    }

    /**
     * 处理businessException自定义异常
     * @param exception         BusinessException
     * @return                  Result
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<String> businessExceptionHandler(BusinessException exception){
        return Result.failed(exception.getMsg());
    }
}
