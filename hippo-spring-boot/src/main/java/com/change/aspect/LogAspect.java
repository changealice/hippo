package com.change.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

/**
 * User: change.long
 * Date: 16/5/25
 * Time: 下午1:04
 */
@Aspect
@Component
@Order(5)
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    LogAspect() {
        logger.info("初始化切面类:{}", getClass().getName());
    }

    /**
     * 定义web切点
     */
    @Pointcut("execution(public * com.change.web..*.*(..))")
    public void webLog() {
        logger.info("加载web日记切面");
    }

    /**
     * 定义service切点
     */
    @Pointcut("execution(public * com.change.service..*.*(..))")
    public void serviceLog() {
        logger.info("加载服务日记切面");
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("RESPONSE : " + ret);
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "ex")
    public void doAfterThrowing(JoinPoint jp, Exception ex) {
        logger.info("ex:{}", ex);
    }
}
