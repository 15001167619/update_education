package com.etycx.common.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {

    long validTime() default 1000L;

    String appKey();

    String interfaceName();

}
