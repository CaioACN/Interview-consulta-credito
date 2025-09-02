package br.com.eicon.gissonline.consultacreditos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuração adicional para o Swagger/OpenAPI
 */
@Configuration
public class SwaggerConfig {
  
  @Bean
  public List<Tag> swaggerTags() {
    return List.of(
      new Tag()
        .name("Créditos")
        .description("Operações relacionadas à consulta de créditos constituídos")
        .externalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
          .description("Documentação da API")
          .url("https://github.com/CaioACN/Interview-consulta-credito"))
    );
  }
  
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("Consulta de Créditos API")
        .description("API RESTful para consulta de créditos constituídos. " +
                    "Permite buscar créditos por número de NFS-e ou por número do crédito. " +
                    "Desenvolvida com Spring Boot 3.4.5 e Java 17.")
        .version("v1.0.0")
        .contact(new Contact()
          .name("Equipe de Desenvolvimento")
          .email("dev@eicon.com.br")
          .url("https://www.eicon.com.br"))
        .license(new License()
          .name("MIT License")
          .url("https://opensource.org/licenses/MIT")))
      .servers(List.of(
        new Server().url("http://localhost:8080").description("Servidor Local"),
        new Server().url("http://localhost:8080").description("Servidor Docker")
      ))
      .components(new Components()
        .addSecuritySchemes("bearerAuth", 
          new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .description("Token JWT para autenticação (opcional)")));
  }
}
