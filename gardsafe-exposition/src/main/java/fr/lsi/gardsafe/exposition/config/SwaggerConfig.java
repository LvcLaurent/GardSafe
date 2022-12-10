package fr.lsi.gardsafe.exposition.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


  ApiInfo apiInfo() {
    return new ApiInfoBuilder()//
        .title("Swagger Service") //
        .description("description")//
        .license("@copyright Laurent SION")//
        .licenseUrl("")//
        .termsOfServiceUrl("") //
        .version("1.0.0") //
        .contact(new Contact("Autheur", "Laurent SION", "laurent.sion@outlook.fr"))//
        .build();
  }
}
