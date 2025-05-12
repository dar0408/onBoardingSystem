package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.dto.PersonalInfoDTO;
import com.bridgelabz.onboarding.dto.ResponseDTO;
import com.bridgelabz.onboarding.service.PersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}/personal-info")
@RequiredArgsConstructor
@Validated
public class PersonalInfoController {
    private final PersonalInfoService service;

    @PutMapping
    public ResponseEntity<ResponseDTO<PersonalInfoDTO>> update(@PathVariable Long id,
                                                               @Valid @RequestBody PersonalInfoDTO dto) {
        try {
            PersonalInfoDTO updated = service.updatePersonalInfo(id, dto);
            return new ResponseEntity<>(new ResponseDTO<>("Personal info updated", updated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error updating personal info", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PersonalInfoDTO>> get(@PathVariable Long id) {
        try {
            PersonalInfoDTO dto = service.getPersonalInfo(id);
            return new ResponseEntity<>(new ResponseDTO<>("Fetched personal info", dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error fetching personal info", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
