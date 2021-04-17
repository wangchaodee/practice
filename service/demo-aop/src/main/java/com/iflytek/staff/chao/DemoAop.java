package com.iflytek.staff.chao;

import com.iflytek.staff.chao.config.AopConfig;
import com.iflytek.staff.chao.service.AnnotationService;
import com.iflytek.staff.chao.service.AnnotationServiceAdd;
import com.iflytek.staff.chao.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
//@EnableAspectJAutoProxy
//@SpringBootApplication
public class DemoAop {
    public static void main(String[] args) {


//        SpringApplication.run(App.class);
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AopConfig.class);

        AnnotationServiceAdd annotationService = acac.getBean(AnnotationServiceAdd.class);
        annotationService.add();

        UserService userService = acac.getBean("userService", UserService.class);
        System.out.println(userService.getUserCount("xxx"));

        AnnotationService as = acac.getBean("annotationService",AnnotationService.class);
        as.add();
        acac.close();

    }
}
