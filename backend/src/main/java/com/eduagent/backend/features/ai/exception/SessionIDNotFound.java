package com.eduagent.backend.features.ai.exception;

public class SessionIDNotFound extends RuntimeException {
  public SessionIDNotFound(String message) {
    super(message);
  }
}
