package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.DocumentDTO;
import com.bridgelabz.onboarding.entity.Document;
import com.bridgelabz.onboarding.exception.CandidateNotFoundException;
import com.bridgelabz.onboarding.repository.CandidateRepository;
import com.bridgelabz.onboarding.repository.DocumentRepository;
import com.bridgelabz.onboarding.util.FileStorageUtil;
import com.bridgelabz.onboarding.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final CandidateRepository candidateRepo;
    private final DocumentRepository documentRepo;
    private final FileStorageUtil fileUtil;

    @Override
    public DocumentDTO uploadDocument(Long candidateId, MultipartFile file) {
        try {
            if (!candidateRepo.existsById(candidateId)) throw new CandidateNotFoundException(candidateId);
            String path = fileUtil.storeFile(file);
            Document doc = Document.builder()
                    .candidateId(candidateId)
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .filePath(path)
                    .verified(false)
                    .build();
            Document saved = documentRepo.save(doc);
            return MapperUtil.toDTO(saved);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading document", e);
        }
    }

    @Override
    @Transactional
    public DocumentDTO verifyDocument(Long documentId) {
        try {
            Document doc = documentRepo.findById(documentId)
                    .orElseThrow(() -> new RuntimeException("Document not found"));
            doc.setVerified(true);
            return MapperUtil.toDTO(doc);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying document", e);
        }
    }

    @Override
    public List<DocumentDTO> listDocuments(Long candidateId) {
        try {
            return documentRepo.findByCandidateId(candidateId).stream()
                    .map(MapperUtil::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching documents", e);
        }
    }
}
