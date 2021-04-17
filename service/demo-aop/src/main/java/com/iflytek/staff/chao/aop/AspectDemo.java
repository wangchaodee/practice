package com.iflytek.staff.chao.aop;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *
 */
@Aspect
@Component
public class AspectDemo {

    @Pointcut("execution(* com.iflytek.staff.chao.service.UserService.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void runStart() {
        System.out.println("系好鞋带");
    }

    @After("pointcut()")
    public void runEnd() {
        System.out.println("跑完了，歇会");
    }

    @AfterReturning("pointcut()")
    public void runFinished() {
        System.out.println("跑步结束");
    }

    @AfterThrowing("pointcut()")
    public void runException() {
        System.out.println("跑步发生异常");
    }


}
