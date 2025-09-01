package br.com.eicon.gissonline.consultacreditos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.eicon.gissonline.consultacreditos.dto.CreditoDto;
import br.com.eicon.gissonline.consultacreditos.dto.CreditoMapper;
import br.com.eicon.gissonline.consultacreditos.service.CreditoService;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

  private final CreditoService service;

  public CreditoController(CreditoService service) {
    this.service = service;
  }

  // GET /api/creditos/{numeroNfse}
  @GetMapping("/{numeroNfse}")
  public ResponseEntity<List<CreditoDto>> listarPorNfse(@PathVariable String numeroNfse) {
    var list = service.listarPorNumeroNfse(numeroNfse).stream()
        .map(CreditoMapper::toDto)
        .toList();
    return ResponseEntity.ok(list);
  }

  // GET /api/creditos/credito/{numeroCredito}
  @GetMapping("/credito/{numeroCredito}")
  public ResponseEntity<CreditoDto> buscarPorNumeroCredito(@PathVariable String numeroCredito) {
    var c = service.buscarPorNumeroCredito(numeroCredito);
    return ResponseEntity.ok(CreditoMapper.toDto(c));
  }
}


