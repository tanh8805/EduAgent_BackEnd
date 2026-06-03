package com.eduagent.backend.features.ai.factory;

import com.eduagent.backend.features.ai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class AIServiceFactory {
  private final Map<String, AIService> aiServices;

  public AIService getAIService(String providerType) {
    AIService service = aiServices.get(providerType.toUpperCase());

    if (service == null) {
      throw new IllegalArgumentException("Không tìm thấy nhà cung cấp AI: " + providerType);
    }
    return service;
  }
}
