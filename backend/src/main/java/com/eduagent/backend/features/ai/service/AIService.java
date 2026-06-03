package com.eduagent.backend.features.ai.service;


import com.eduagent.backend.features.ai.dto.MessageDTO;

import java.util.List;

public interface AIService {
  String generateText(List<MessageDTO> prompt);
}
