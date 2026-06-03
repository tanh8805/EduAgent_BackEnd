package com.eduagent.backend.features.ai.dto;

import lombok.Data;

import java.util.*;
import java.io.*;

@Data
public class UsageDTO {
  private int prompt_tokens;
  private int completion_tokens;
  private int total_tokens;
}
