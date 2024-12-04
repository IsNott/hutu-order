package org.nott.common.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    /**
     * 缓存Key
     */
    String key();

    /**
     * 业务id，key+id存在时使用Hashset
     */
    String item();

    /**
     * 过期时间
     */
    long expire() default -1;
}
