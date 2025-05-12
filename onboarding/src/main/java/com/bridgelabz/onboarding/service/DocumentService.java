package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    DocumentDTO uploadDocument(Long candidateId, MultipartFile file);
    DocumentDTO verifyDocument(Long documentId);
    List<DocumentDTO> listDocuments(Long candidateId);
}
