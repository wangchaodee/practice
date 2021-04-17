package com.iflytek.staff.chao.web;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by chaowang5 on 2017/12/27.
 */
//@RestController
//@EnableSwagger2
public class SwaggerController {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo("sample of springboot", "sample of springboot",
                null, null, null, null, null);
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.iflytek.staff"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
        return docket;

    }
}
