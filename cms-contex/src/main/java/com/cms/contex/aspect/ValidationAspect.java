package com.cms.contex.aspect;

import com.cms.contex.foundation.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@Component
@Aspect
//aop的实现
public class ValidationAspect {

    @Pointcut("@annotation(com.cms.core.annotation.DoValid)")
    public void pointcut(){
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        for(Object arg: point.getArgs()){
            if(arg instanceof BeanPropertyBindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    return Result.failed(bindingResult.getFieldError().getDefaultMessage());
                }
            }
        }
        return point.proceed();
    }
}
