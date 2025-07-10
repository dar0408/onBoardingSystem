package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.dto.DocumentDTO;
import com.bridgelabz.onboarding.dto.ResponseDTO;
import com.bridgelabz.onboarding.service.DocumentService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Hidden
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/candidates/{id}/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<ResponseDTO<DocumentDTO>> upload(@PathVariable Long id,
                                                           @RequestParam("file") MultipartFile file) {
        try {
            DocumentDTO dto = documentService.uploadDocument(id, file);
            return new ResponseEntity<>(new ResponseDTO<>("Uploaded", dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error uploading document", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{docId}/verify")
    public ResponseEntity<ResponseDTO<DocumentDTO>> verify(@PathVariable Long id,
                                                           @PathVariable Long docId) {
        try {
            DocumentDTO dto = documentService.verifyDocument(docId);
            return new ResponseEntity<>(new ResponseDTO<>("Verified", dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error verifying document", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<DocumentDTO>>> list(@PathVariable Long id) {
        try {
            List<DocumentDTO> list = documentService.listDocuments(id);
            return new ResponseEntity<>(new ResponseDTO<>("List", list), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error fetching documents", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
