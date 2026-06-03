package com.eduagent.backend.features.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIResponseDTO {
    private Integer sessionId;
    private String result;
}