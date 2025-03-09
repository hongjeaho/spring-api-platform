package com.platform.common.web.config;

import com.platform.common.web.util.JwtTokenUtil;
import com.platform.datasource.base.auth.AuthUser;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class SwaggerConfig {

  @Bean
  OpenAPI openAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(apiInfo());
  }

  private Info apiInfo() {
    return new Info()
        .title("service-platform-web")
        .description("platform API")
        .version("1.0");
  }

  @Bean
  public String createSwaggerJwtToken() {
    AuthUser authUser = new AuthUser();
    authUser.setId("admin");
    return "Bearer " + JwtTokenUtil.makeAuthToken(authUser);
  }
}
