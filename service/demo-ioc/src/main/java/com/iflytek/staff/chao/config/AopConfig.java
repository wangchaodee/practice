package com.iflytek.staff.chao.config;

import com.iflytek.staff.chao.service.UserService;
import com.iflytek.staff.chao.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.iflytek.staff.chao")
public class AopConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }
}
