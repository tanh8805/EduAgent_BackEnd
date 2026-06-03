package com.eduagent.backend.features.ai.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "session_id", nullable = false)
  private Integer sessionId;

  @Column(name = "sender", nullable = false, length = 50)
  private String sender; // 'USER' hoặc 'AI'

  @Column(name = "message_text", nullable = false, columnDefinition = "TEXT")
  private String messageText;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }
}