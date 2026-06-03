package com.eduagent.backend.features.ai.dto;

import lombok.Data;
import java.util.List;

@Data
public class GeminiResponseDTO {
  private String id;
  private String object;
  private long created;
  private String model;
  private List<ChoiceDTO> choices;
  private UsageDTO usage;
}

