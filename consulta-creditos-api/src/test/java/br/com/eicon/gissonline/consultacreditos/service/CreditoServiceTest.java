package br.com.eicon.gissonline.consultacreditos.service;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.eicon.gissonline.consultacreditos.domain.Credito;
import br.com.eicon.gissonline.consultacreditos.exception.NotFoundException;
import br.com.eicon.gissonline.consultacreditos.repository.CreditoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreditoServiceTest {

  @Test
  void buscarPorNumeroCredito_quandoNaoExistir_lanca404() {
    var repo = Mockito.mock(CreditoRepository.class);
    Mockito.when(repo.findByNumeroCredito("X")).thenReturn(Optional.empty());

    var svc = new CreditoService(repo);

    assertThrows(NotFoundException.class, () -> svc.buscarPorNumeroCredito("X"));
  }

  @Test
  void buscarPorNumeroCredito_quandoExistir_retorna() {
    var repo = Mockito.mock(CreditoRepository.class);
    var c = new Credito(); c.setNumeroCredito("123");
    Mockito.when(repo.findByNumeroCredito("123")).thenReturn(Optional.of(c));

    var svc = new CreditoService(repo);

    assertEquals("123", svc.buscarPorNumeroCredito("123").getNumeroCredito());
  }
}


