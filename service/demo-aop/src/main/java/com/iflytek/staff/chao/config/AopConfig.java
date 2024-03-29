package com.iflytek.staff.chao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.iflytek.staff.chao")
@EnableAspectJAutoProxy
public class AopConfig {
}
