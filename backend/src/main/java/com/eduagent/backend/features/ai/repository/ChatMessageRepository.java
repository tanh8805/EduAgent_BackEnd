package com.eduagent.backend.features.ai.repository;

import com.eduagent.backend.features.ai.entity.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

  @Query("SELECT m FROM ChatMessage m WHERE m.sessionId = :sessionId ORDER BY m.id DESC")
  List<ChatMessage> findRecentMessages(@Param("sessionId") Integer sessionId, Pageable pageable);
}