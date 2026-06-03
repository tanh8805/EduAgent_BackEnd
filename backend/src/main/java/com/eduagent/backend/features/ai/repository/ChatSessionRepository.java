package com.eduagent.backend.features.ai.repository;

import com.eduagent.backend.features.ai.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.io.*;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer> {

}
