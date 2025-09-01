package br.com.eicon.gissonline.consultacreditos.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFound(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
      "timestamp", OffsetDateTime.now().toString(),
      "status", 404,
      "error", "Not Found",
      "message", ex.getMessage()
    ));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleDefault(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
      "timestamp", OffsetDateTime.now().toString(),
      "status", 500,
      "error", "Internal Server Error",
      "message", ex.getMessage()
    ));
  }
}


