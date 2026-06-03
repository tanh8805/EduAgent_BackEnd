package com.eduagent.backend.features.ai.service.impl;

import com.eduagent.backend.features.ai.dto.MessageDTO;
import com.eduagent.backend.features.ai.service.AIService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OPENAI")
public class OpenAIServiceImpl implements AIService {

  @Override
  public String generateText(List<MessageDTO> prompt) {
    return "OPENAI";
  }
}
