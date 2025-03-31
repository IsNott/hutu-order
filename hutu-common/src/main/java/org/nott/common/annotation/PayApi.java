package org.nott.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PayApi {

    @AliasFor("code")
    String value() default "";

    @AliasFor("value")
    String code() default "";

}
