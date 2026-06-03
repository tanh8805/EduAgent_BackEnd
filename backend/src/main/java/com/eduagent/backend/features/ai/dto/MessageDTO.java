package com.eduagent.backend.features.ai.dto;

import lombok.Data;

import java.util.*;
import java.io.*;

@Data
public class MessageDTO {
  private String role;
  private String content;
}
