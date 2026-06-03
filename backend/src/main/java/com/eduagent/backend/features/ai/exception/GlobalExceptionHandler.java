package com.eduagent.backend.features.ai.exception;
import com.eduagent.backend.features.ai.dto.ErrorMessage;
import com.eduagent.backend.features.ai.dto.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(SessionIDNotFound.class)
  public ResponseEntity<?> handleSessionIDNotFound(SessionIDNotFound ex) {
    return ResponseEntity.badRequest().body(ErrorMessage.builder().message(ex.getMessage()).build());
  }
}
