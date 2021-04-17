package com.iflytek.staff.chao.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PointCutAnnotation {

    String name();
}
