package com.eduagent.backend.features.ai.service;

import org.springframework.stereotype.Service;

@Service("OPENAI")
public class OpenAIServiceImpl implements AIService {

  @Override
  public String generateText(String prompt) {
    return "OPENAI";
  }
}
