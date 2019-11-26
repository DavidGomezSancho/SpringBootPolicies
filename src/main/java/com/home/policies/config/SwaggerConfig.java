package com.home.policies.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	private MessageSource messageSource;
	
	@Bean
    public Docket api() {	
		Parameter headerParam = new ParameterBuilder().name("Authorization").parameterType("header")
	               .modelRef(new ModelRef("string")).description("Bearer <token>")
	               .defaultValue("Bearer " + messageSource.getMessage("defaulttoken", null, LocaleContextHolder.getLocale()))
	               .required(true).build();
		
		return new Docket(DocumentationType.SWAGGER_2)
			.select()                                  
			.apis(RequestHandlerSelectors.basePackage("com.home.policies.policies"))              
			.paths(PathSelectors.ant("/api/mypolicies/**"))                          
			.build()
			.tags(new Tag("Policy", "Policy Controller description"), 
					new Tag("Vehicle", "Vehicle Controller description"),
					new Tag("Taker", "Taker Controller description"))
			.globalOperationParameters(Arrays.asList(headerParam));
    }
}
