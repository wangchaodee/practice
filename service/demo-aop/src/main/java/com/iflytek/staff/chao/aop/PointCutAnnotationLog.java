package com.iflytek.staff.chao.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PointCutAnnotationLog {

    @Pointcut("@annotation(com.iflytek.staff.chao.aop.PointCutAnnotation)")
    public void annotationPointCut() {
        System.out.println("annotationPointCut");
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        PointCutAnnotation action = method.getAnnotation(PointCutAnnotation.class);
        System.out.println("注解式拦截" + action.name());
    }

//    @Before("execution(* com.iflytek.staff.chao.service.UerService.*(..))")
//    public void before(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        System.out.println("方法规则式拦截，" + method.getName());
//    }

}
