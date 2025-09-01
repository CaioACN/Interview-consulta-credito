package br.com.eicon.gissonline.consultacreditos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eicon.gissonline.consultacreditos.domain.Credito;

import java.util.List;
import java.util.Optional;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
  List<Credito> findByNumeroNfse(String numeroNfse);
  Optional<Credito> findByNumeroCredito(String numeroCredito);
  
}


