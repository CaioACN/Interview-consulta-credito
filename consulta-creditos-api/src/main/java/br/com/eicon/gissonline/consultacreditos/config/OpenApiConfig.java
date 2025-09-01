package br.com.eicon.gissonline.consultacreditos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {
  @Bean
  OpenAPI openAPI() {
    return new OpenAPI().info(new Info()
      .title("Consulta de Créditos API")
      .description("Endpoints para consulta de créditos constituídos")
      .version("v1"));
  }
}


