package com.schoolsell.util.myAspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截登录操作
 */

@Aspect
@Component
public class LoginAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    //拦截用户登录
    @Pointcut(value = "execution(public * com.schoolsell.controller..*.*(..))")
    public void loginLog() {
    }

    @Before("loginLog()")
    public void beforeLogin(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append("/********************用户发起请求****************/");

        //记录一下内容
        logger.info(stringBuilder.toString());
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName());

    }

    @AfterReturning(returning = "object", pointcut = "loginLog()")
    public void afterLogin(Object object) throws Throwable {
        logger.info("方法返回值:" + object);
        logger.info("/**********************请求结束******************/");
    }

//    @AfterThrowing(throwing = "ex",pointcut = "loginLog()")
//    public void exceptionLogin(Joinpoint joinpoint,Exception ex) {
//
//    }

}
