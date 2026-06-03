package com.eduagent.backend.features.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.io.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String message;
}
