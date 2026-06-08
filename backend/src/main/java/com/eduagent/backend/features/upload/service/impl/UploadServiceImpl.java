package com.eduagent.backend.features.upload.service.impl;

import com.eduagent.backend.features.upload.dto.DocumentUploadResponseDTO;
import com.eduagent.backend.features.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

  private final WebClient webClient;

  @Value("${fastapi.uri}")
  private String fastApiUri;

  @Override
  public DocumentUploadResponseDTO uploadDocumentToFastApi(MultipartFile file, Integer sessionId, Integer userId) {
    if (file == null || file.isEmpty()) {
      return new DocumentUploadResponseDTO("error", "File is empty or invalid");
    }

    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("file", file.getResource()).filename(file.getOriginalFilename());
    builder.part("session_id", sessionId);
    builder.part("user_id", userId);

    try {
      return webClient.post()
              .uri(fastApiUri + "/api/upload")
              .contentType(MediaType.MULTIPART_FORM_DATA)
              .body(BodyInserters.fromMultipartData(builder.build()))
              .retrieve()
              .bodyToMono(DocumentUploadResponseDTO.class)
              .block();
    } catch (Exception e) {
      e.printStackTrace();
      return new DocumentUploadResponseDTO("error", "Failed to forward document to FastAPI: " + e.getMessage());
    }
  }
}