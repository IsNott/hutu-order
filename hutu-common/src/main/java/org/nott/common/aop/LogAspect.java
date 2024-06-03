package org.nott.common.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2024-6-3
 */

@Aspect
@Component
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * org.nott.*.controller..*.*(..))")
    public void privilege() {}

    /**
     * 环绕通知
     *
     * @param pjd
     * @return
     * @throws Throwable
     */
    @Around("privilege()")
    public Object arount(ProceedingJoinPoint pjd) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = pjd.getTarget().getClass().getName();
        String methodName = pjd.getSignature().getName();
        Object[] args = pjd.getArgs();
        if(args.length > 0){
            try {
                String params = JSON.toJSONString(args[0]);
                log.info("{}.{}()[方法参数]：{}", className, methodName, params);
            } catch (Exception e) {
                log.info("{}.{}()[方法参数打印异常]：{}", className, methodName, e.getMessage());
            }
        }
        Object result = pjd.proceed();
        try {
            String s = JSON.toJSONString(result);
            log.info("{}.{}()[方法执行结果]：{}", className, methodName, s);
        } catch (Exception e) {
            log.info("{}.{}()[方法执行结果打印异常]：{}", className, methodName, e);
        }
        long time = System.currentTimeMillis() - startTime;
        log.info("{}.{}()[方法执行完毕]：{}{}", className, methodName, time, " ms");
        return result;
    }
}