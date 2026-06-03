package com.eduagent.backend.features.ai.dto;

import lombok.Data;

@Data
public class AIRequestDTO {
  private Integer sessionId;
  private String provider;
  private String prompt;
}