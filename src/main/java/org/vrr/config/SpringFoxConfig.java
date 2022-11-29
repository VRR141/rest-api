package org.vrr.config;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
        return docket;
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Vladislav Ryazantsev",
                "t.me/VRR14",
                "v.r.ryazantsev@gmail.com");
        return new ApiInfo(
            "Simple rest app",
                "Spring MVC, Hibernate, MySQL, Tomcat 9.0.681",
                "1.0.0",
                "",
                contact,
                "", "", Collections.emptyList()
                );
    }

}