package org.nott.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.nott.common.ResponseEntity;
import org.nott.common.annotation.ClearCache;
import org.nott.common.annotation.RedisCache;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
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

@Slf4j
@Aspect // 定义切面
@Component // 交给Spring管理
public class CacheAspect {

    @Resource
    private RedisUtils redisUtils;

    // @annotation(...RedisCache)表示切点为被RedisCache注解注释的所有方法
    //@Before("") // 前置，方法执行前
    //@AfterReturning("") // 后置，在返回返回值后执行
    //@AfterThrowing("") // 后置，在方法抛出异常执行
    //@After("") // 后置，在方法执行完毕后执行

    @Around("@annotation(org.nott.common.annotation.RedisCache)") // 环绕，在方法执行时（实际上在切点匹配JoinPoint时，在@Before前面执行）
    public Object aroundRedisCache(ProceedingJoinPoint point) throws Throwable {
        // 获取连接点的方法名称、类、实际参数
        String name = point.getSignature().getName();
        Object target = point.getTarget();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class[] parameterTypes = signature.getParameterTypes();
        Object[] args = point.getArgs();

        // 通过反射获取业务方法
        Method method = target.getClass().getMethod(name, parameterTypes);
        ResponseEntity<?> methodResult;

        RedisCache annotation = method.getAnnotation(RedisCache.class);
        String annotationItem = annotation.item();
        String key = annotation.key();

        // 存在参数并带有业务id值
        String elValue = HutuUtils.parseSpEl(method, args, annotationItem, String.class);
        Object redisResult = this.doRedisGet(key, elValue);

        // 缓存存在时直接返回
        if (HutuUtils.isNotEmpty(redisResult)) {
            return ResponseEntity.successData(redisResult);
        }
        // 不存在时存入Redis
        methodResult = (ResponseEntity<?>) point.proceed();
        Object data = methodResult.getData();
        Type[] genericInterfaces = data.getClass().getGenericInterfaces();
        Type type = Arrays.stream(genericInterfaces)
                .filter(r -> Serializable.class == r)
                .findAny().orElse(null);
        HutuUtils.requireNotNull(type, "使用缓存的方法返回值必须实现Serializable接口");

        this.doRedisAdd(method.getName(), elValue, data, annotation.expire());
        return methodResult;
    }

    @Around("@annotation(org.nott.common.annotation.ClearCache)")
    public Object aroundClearCache(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String signatureName = signature.getName();
        Method method = pjp.getTarget().getClass().getMethod(signatureName, signature.getParameterTypes());
        ClearCache annotation = method.getAnnotation(ClearCache.class);
        String item = annotation.item();
        String key = annotation.key();
        boolean runBefore = annotation.runBefore();
        if (runBefore) {
            this.doRedisDel(key, item);
        }
        Object result = pjp.proceed();
        if (!runBefore) {
            this.doRedisDel(key, item);
        }
        return result;
    }

    public void doRedisAdd(String key, String item, Object val, long time) {
        boolean hasItemId = StringUtils.isNotEmpty(item);
        if (hasItemId) {
            redisUtils.set(key, val, time);
        } else {
            redisUtils.hset(key, item, val);
        }
    }

    public Object doRedisGet(String key, String elVal) {
        boolean hasItemId = StringUtils.isNotEmpty(elVal);
        if (hasItemId) {
            return redisUtils.get(key);
        } else {
            return redisUtils.hget(key, elVal);
        }
    }


    public void doRedisDel(String key, String item) {
        boolean hasItemId = StringUtils.isNotEmpty(item);
        if (hasItemId) {
            redisUtils.delByKey(key);
        } else {
            redisUtils.hdel(key, item);
        }
    }

}
