package br.com.eicon.gissonline.consultacreditos.controller;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.eicon.gissonline.consultacreditos.domain.Credito;
import br.com.eicon.gissonline.consultacreditos.service.CreditoService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CreditoControllerTest {

  @Test
  void listarPorNfse_ok() throws Exception {
    var service = mock(CreditoService.class);
    var c = new Credito(); c.setNumeroCredito("123"); c.setNumeroNfse("7891011");
    when(service.listarPorNumeroNfse("7891011")).thenReturn(List.of(c));

    MockMvc mvc = MockMvcBuilders.standaloneSetup(new CreditoController(service)).build();

    mvc.perform(get("/api/creditos/7891011").accept(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }
}

