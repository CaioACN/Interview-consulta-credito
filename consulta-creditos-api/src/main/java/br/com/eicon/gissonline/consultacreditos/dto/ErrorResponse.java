package br.com.eicon.gissonline.consultacreditos.dto;

import java.time.OffsetDateTime;

/**
 * DTO para respostas de erro da API
 */
public record ErrorResponse(
    OffsetDateTime timestamp,
    int status,
    String error,
    String message
) {}
