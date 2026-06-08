package com.eduagent.backend.features.upload.service;

import com.eduagent.backend.features.upload.dto.DocumentUploadResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
  DocumentUploadResponseDTO uploadDocumentToFastApi(MultipartFile file, Integer sessionId, Integer userId);
}