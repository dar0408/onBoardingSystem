package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.dto.EducationalInfoDTO;
import com.bridgelabz.onboarding.dto.ResponseDTO;
import com.bridgelabz.onboarding.service.EducationalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}/educational-info")
@RequiredArgsConstructor
@Validated
public class EducationalInfoController {
    private final EducationalInfoService service;

    @PutMapping
    public ResponseEntity<ResponseDTO<EducationalInfoDTO>> update(@PathVariable Long id,
                                                                  @Valid @RequestBody EducationalInfoDTO dto) {
        try {
            EducationalInfoDTO updated = service.updateEducationalInfo(id, dto);
            return new ResponseEntity<>(new ResponseDTO<>("Educational info updated", updated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error updating educational info", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<EducationalInfoDTO>> get(@PathVariable Long id) {
        try {
            EducationalInfoDTO dto = service.getEducationalInfo(id);
            return new ResponseEntity<>(new ResponseDTO<>("Fetched educational info", dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error fetching educational info", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
