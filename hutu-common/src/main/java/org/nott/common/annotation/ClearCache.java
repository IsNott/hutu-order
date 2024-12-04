package org.nott.common.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClearCache {

    String key();

    String item();

    boolean runBefore() default false;
}
