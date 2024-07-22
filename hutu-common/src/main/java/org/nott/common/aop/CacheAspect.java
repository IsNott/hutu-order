package org.nott.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.nott.common.ResponseEntity;
import org.nott.common.annotation.RedisCache;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author Nott
 * @date 2024-7-22
 */

@Aspect
@Slf4j
@Component
public class CacheAspect {

    @Resource
    private RedisUtils redisUtils;

    @Around("@annotation(org.nott.common.annotation.RedisCache)")
    public Object around(ProceedingJoinPoint point) {
        String name = point.getSignature().getName();
        Object target = point.getTarget();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class[] parameterTypes = signature.getParameterTypes();
        Object[] args = point.getArgs();
        DefaultParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        Method method = null;
        Object redisResult;
        ResponseEntity<?> methodResult;
        String elValue = "";
        boolean hasArg = parameterTypes.length > 0;
        try {
            method = target.getClass().getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        RedisCache annotation = method.getAnnotation(RedisCache.class);

        if (hasArg) {
            String[] parameterNames = defaultParameterNameDiscoverer.getParameterNames(method);
            StandardEvaluationContext ctx = new StandardEvaluationContext();
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(annotation.item());
            for (int i = 0; i < parameterNames.length; i++) {
                ctx.setVariable(parameterNames[i], args[i]);
            }
            elValue = expression.getValue(ctx, String.class);
            redisResult = redisUtils.hget(annotation.key(), elValue);
        } else {
            redisResult = redisUtils.get(annotation.key());
        }
        if (HutuUtils.isNotEmpty(redisResult)) {
            return ResponseEntity.successData(redisResult);
        } else {
            try {
                methodResult =(ResponseEntity<?>) point.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Object data = methodResult.getData();
            Type[] genericInterfaces = data.getClass().getGenericInterfaces();
            Type type = Arrays.stream(genericInterfaces)
                    .filter(r -> Serializable.class == r)
                    .findAny().orElse(null);
            HutuUtils.requireNotNull(type,"使用缓存的方法返回值必须实现Serializable接口");
            if(hasArg){
                redisUtils.hset(method.getName(), elValue, data, annotation.expire());
            } else {
                redisUtils.set(method.getName(),data, annotation.expire());
            }
            return methodResult;
        }
    }

}
