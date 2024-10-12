package org.nott.common.aop;

import cn.dev33.satoken.stp.StpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 需用户登录态AOP组件
 * @author Nott
 * @date 2024-10-12
 */
@Aspect
@Component
public class LoginAspect {

    private final Logger log = LoggerFactory.getLogger(LoginAspect.class);

    @Around("@annotation(org.nott.common.annotation.JustLogin)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        try {
            StpUtil.getLoginIdAsLong();
        } catch (Exception e) {
            log.error("Method {} executed error,User dont login",pjp.getSignature().getName());
            throw e;
        }
        return pjp.proceed();
    }
}
