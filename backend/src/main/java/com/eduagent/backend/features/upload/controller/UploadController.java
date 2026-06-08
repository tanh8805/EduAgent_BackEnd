package com.eduagent.backend.features.upload.controller;

import com.eduagent.backend.features.upload.dto.DocumentUploadResponseDTO;
import com.eduagent.backend.features.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UploadController {

  private final UploadService uploadService;

  @PostMapping("/upload")
  public ResponseEntity<?> upload(
          @RequestParam("file") MultipartFile file,
          @RequestParam("sessionId") Integer sessionId,
          @RequestParam("userId") Integer userId) {

    DocumentUploadResponseDTO response = uploadService.uploadDocumentToFastApi(file, sessionId, userId);

    if ("success".equalsIgnoreCase(response.getStatus())) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }
}