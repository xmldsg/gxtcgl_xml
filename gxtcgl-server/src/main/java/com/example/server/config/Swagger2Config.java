package com.example.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置类
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.server.controller"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(securitySchemes());
	}

	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("高校停车管理")
				.description("高校停车管理")
				.contact(new Contact("xueminglu","http:localhost:8081/doc.html","xxxx@xxxx.com"))
				.version("1.0")
				.build();
	}

	private List<ApiKey> securitySchemes(){
		//设置请求头信息
		List<ApiKey> result= new ArrayList<>();
		ApiKey apiKey = new ApiKey("Authorization","Authorization","Header");
		result.add(apiKey);
		return result;
	}

	private SecurityContext getContextByPath(String pathRegex) {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(pathRegex))
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		List<SecurityReference> result = new ArrayList<>();
		AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		result.add(new SecurityReference("Authorization",authorizationScopes));
		return result;
	}

}