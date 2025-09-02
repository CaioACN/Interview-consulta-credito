package br.com.eicon.gissonline.consultacreditos.controller;

import br.com.eicon.gissonline.consultacreditos.dto.CreditoDto;
import br.com.eicon.gissonline.consultacreditos.dto.CreditoMapper;
import br.com.eicon.gissonline.consultacreditos.dto.ErrorResponse;
import br.com.eicon.gissonline.consultacreditos.service.CreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Créditos", description = "Endpoints para consulta de créditos constituídos", 
     externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
       description = "Documentação da API",
       url = "https://github.com/CaioACN/Interview-consulta-credito"
     ))
@RestController
@RequestMapping(path = "/api/creditos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditoController {

  private final CreditoService service;

  public CreditoController(CreditoService service) {
    this.service = service;
  }

  /**
   * GET /api/creditos/{numeroNfse}
   */
  @Operation(
      summary = "Listar créditos por NFS-e",
      description = "Retorna a lista de créditos constituídos para o número de NFS-e informado. " +
                   "Este endpoint permite buscar todos os créditos associados a uma NFS-e específica.",
      operationId = "listarCreditosPorNfse"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Lista de créditos retornada com sucesso",
          content = @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = CreditoDto.class),
                  minItems = 0,
                  uniqueItems = true
              ),
              examples = {
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Sucesso com dados",
                      summary = "Exemplo de resposta com créditos encontrados",
                      value = "[{\"numeroCredito\":\"123456\",\"numeroNfse\":\"7891011\",\"dataConstituicao\":\"2024-02-25\",\"valorIssqn\":1500.75,\"tipoCredito\":\"ISSQN\",\"simplesNacional\":\"Sim\",\"aliquota\":5.0,\"valorFaturado\":30000.00,\"valorDeducao\":5000.00,\"baseCalculo\":25000.00}]"
                  ),
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Sucesso sem dados",
                      summary = "Exemplo de resposta sem créditos encontrados",
                      value = "[]"
                  )
              }
          )
      ),
      @ApiResponse(
          responseCode = "400", 
          description = "Parâmetro inválido - NFS-e deve conter apenas números", 
          content = @Content(
              schema = @Schema(implementation = ErrorResponse.class),
              examples = {
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Parâmetro inválido",
                      value = "{\"timestamp\":\"2024-01-01T00:00:00Z\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"NFS-e deve conter apenas números\"}"
                  )
              }
          )
      ),
      @ApiResponse(
          responseCode = "500", 
          description = "Erro interno do servidor", 
          content = @Content(
              schema = @Schema(implementation = ErrorResponse.class),
              examples = {
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Erro interno",
                      value = "{\"timestamp\":\"2024-01-01T00:00:00Z\",\"status\":500,\"error\":\"Internal Server Error\",\"message\":\"Erro ao processar requisição\"}"
                  )
              }
          )
      )
  })
  // restringe a captura para somente dígitos, evitando conflito com /credito/{...}
  @GetMapping("/{numeroNfse:\\d+}")
  public ResponseEntity<List<CreditoDto>> listarPorNfse(
      @Parameter(
          description = "Número da NFS-e para buscar créditos associados", 
          example = "7891011",
          required = true,
          schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string", pattern = "\\d+")
      )
      @PathVariable String numeroNfse
  ) {
    var list = service.listarPorNumeroNfse(numeroNfse).stream()
        .map(CreditoMapper::toDto)
        .toList();
    return ResponseEntity.ok(list); // pode retornar lista vazia (200)
  }

  /**
   * GET /api/creditos/credito/{numeroCredito}
   */
  @Operation(
      summary = "Obter crédito por número",
      description = "Retorna os detalhes de um crédito constituído a partir do seu número. " +
                   "Este endpoint permite buscar informações detalhadas de um crédito específico.",
      operationId = "buscarCreditoPorNumero"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Crédito encontrado com sucesso",
          content = @Content(
              schema = @Schema(implementation = CreditoDto.class),
              examples = {
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Crédito encontrado",
                      summary = "Exemplo de resposta com crédito encontrado",
                      value = "{\"numeroCredito\":\"123456\",\"numeroNfse\":\"7891011\",\"dataConstituicao\":\"2024-02-25\",\"valorIssqn\":1500.75,\"tipoCredito\":\"ISSQN\",\"simplesNacional\":\"Sim\",\"aliquota\":5.0,\"valorFaturado\":30000.00,\"valorDeducao\":5000.00,\"baseCalculo\":25000.00}"
                  )
              }
          )
      ),
      @ApiResponse(
          responseCode = "404", 
          description = "Crédito não encontrado", 
          content = @Content(
              schema = @Schema(implementation = ErrorResponse.class),
              examples = {
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Crédito não encontrado",
                      value = "{\"timestamp\":\"2024-01-01T00:00:00Z\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Crédito não encontrado: 999999\"}"
                  )
              }
          )
      ),
      @ApiResponse(
          responseCode = "400", 
          description = "Parâmetro inválido", 
          content = @Content(
              schema = @Schema(implementation = ErrorResponse.class),
              examples = {
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Parâmetro inválido",
                      value = "{\"timestamp\":\"2024-01-01T00:00:00Z\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Número do crédito é obrigatório\"}"
                  )
              }
          )
      ),
      @ApiResponse(
          responseCode = "500", 
          description = "Erro interno do servidor", 
          content = @Content(
              schema = @Schema(implementation = ErrorResponse.class),
              examples = {
                  @io.swagger.v3.oas.annotations.media.ExampleObject(
                      name = "Erro interno",
                      value = "{\"timestamp\":\"2024-01-01T00:00:00Z\",\"status\":500,\"error\":\"Internal Server Error\",\"message\":\"Erro ao processar requisição\"}"
                  )
              }
          )
      )
  })
  @GetMapping("/credito/{numeroCredito}")
  public ResponseEntity<CreditoDto> buscarPorNumeroCredito(
      @Parameter(
          description = "Número do crédito para buscar detalhes", 
          example = "123456",
          required = true,
          schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string", minLength = 1)
      )
      @PathVariable String numeroCredito
  ) {
    var c = service.buscarPorNumeroCredito(numeroCredito);
    return ResponseEntity.ok(CreditoMapper.toDto(c));
  }
}
