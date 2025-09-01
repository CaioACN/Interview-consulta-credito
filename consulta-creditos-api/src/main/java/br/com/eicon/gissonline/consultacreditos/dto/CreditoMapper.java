package br.com.eicon.gissonline.consultacreditos.dto;

import br.com.eicon.gissonline.consultacreditos.domain.Credito;

public final class CreditoMapper {
  private CreditoMapper(){}

  public static CreditoDto toDto(Credito c) {
    return new CreditoDto(
        c.getNumeroCredito(),
        c.getNumeroNfse(),
        c.getDataConstituicao(),
        c.getValorIssqn(),
        c.getTipoCredito(),
        c.isSimplesNacional() ? "Sim" : "Não",
        c.getAliquota(),
        c.getValorFaturado(),
        c.getValorDeducao(),
        c.getBaseCalculo()
    );
  }
}



