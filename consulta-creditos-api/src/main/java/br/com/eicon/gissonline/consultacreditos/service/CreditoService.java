package br.com.eicon.gissonline.consultacreditos.service;


import org.springframework.stereotype.Service;

import br.com.eicon.gissonline.consultacreditos.domain.Credito;
import br.com.eicon.gissonline.consultacreditos.exception.NotFoundException;
import br.com.eicon.gissonline.consultacreditos.repository.CreditoRepository;

import java.util.List;

@Service
public class CreditoService {

  private final CreditoRepository repo;

  public CreditoService(CreditoRepository repo) {
    this.repo = repo;
  }

  public List<Credito> listarPorNumeroNfse(String nfse) {
    return repo.findByNumeroNfse(nfse);
  }

  public Credito buscarPorNumeroCredito(String numeroCredito) {
    return repo.findByNumeroCredito(numeroCredito)
        .orElseThrow(() -> new NotFoundException("Crédito não encontrado: " + numeroCredito));
  }
}



