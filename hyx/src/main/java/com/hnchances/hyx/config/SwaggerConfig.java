package com.hnchances.hyx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger的配置类用于测试后端代码
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     *
     * 创建swagger的Docket的bean实例
     *
     */
    @Bean
    public Docket docket(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("学生成绩管理系统")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hnchances.hyx.controller"))
                .build();
    }

    /**
     *
     * 创建swagger的相关作者信息
     *
     */
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                 // 文档页标题
                .title("学生成绩管理系统的API文档")
                // 联系人信息
                .contact(new Contact("hyx", "", "yuexuan_huang@126.com"))
                // 文档版本号
                .version("1.0")
                // 描述
                .description("API 描述")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}