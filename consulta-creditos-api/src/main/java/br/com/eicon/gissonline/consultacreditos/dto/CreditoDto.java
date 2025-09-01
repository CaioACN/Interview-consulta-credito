package br.com.eicon.gissonline.consultacreditos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreditoDto(
    String numeroCredito,
    String numeroNfse,
    LocalDate dataConstituicao,
    BigDecimal valorIssqn,
    String tipoCredito,
    String simplesNacional, // "Sim" / "Não"
    BigDecimal aliquota,
    BigDecimal valorFaturado,
    BigDecimal valorDeducao,
    BigDecimal baseCalculo
) {}

