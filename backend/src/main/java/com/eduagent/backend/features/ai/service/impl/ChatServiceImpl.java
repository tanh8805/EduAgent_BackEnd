package com.eduagent.backend.features.ai.service.impl;

import com.eduagent.backend.features.ai.dto.MessageDTO;
import com.eduagent.backend.features.ai.entity.ChatMessage;
import com.eduagent.backend.features.ai.repository.ChatMessageRepository;
import com.eduagent.backend.features.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
  private final ChatMessageRepository chatMessageRepository;

  @Override
  public ChatMessage saveMessage(Integer sessionId, String sender, String text) {
    ChatMessage message = ChatMessage.builder()
            .sessionId(sessionId)
            .sender(sender)
            .messageText(text)
            .build();
    return chatMessageRepository.save(message);
  }

  @Override
  public List<MessageDTO> getChatHistoryWindow(Integer sessionId, int windowSize) {
    List<ChatMessage> recentMessages = chatMessageRepository.findRecentMessages(
            sessionId,
            PageRequest.of(0, windowSize)
    );
    Collections.reverse(recentMessages);
    return recentMessages.stream().map(msg -> {
      String role = "USER".equalsIgnoreCase(msg.getSender()) ? "user" : "AI";
      return new MessageDTO(role, msg.getMessageText());
    }).collect(Collectors.toList());
  }
}
