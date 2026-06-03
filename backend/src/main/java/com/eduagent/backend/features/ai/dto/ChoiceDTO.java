package com.eduagent.backend.features.ai.dto;

import lombok.Data;

import java.util.*;
import java.io.*;

@Data
public class ChoiceDTO {
  private int index;
  private MessageDTO message;
  private String finish_reason;
}
