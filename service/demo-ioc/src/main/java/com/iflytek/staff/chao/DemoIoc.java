package com.iflytek.staff.chao;

import com.iflytek.staff.chao.config.AopConfig;
import com.iflytek.staff.chao.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class DemoIoc {
    public static void main(String[] args) {


        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AopConfig.class);

        UserService userService = acac.getBean(UserService.class);
        System.out.println(userService.getUserCount("sfs"));


    }
}
