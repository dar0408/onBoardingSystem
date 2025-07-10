package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.dto.BankInfoDTO;
import com.bridgelabz.onboarding.dto.ResponseDTO;
import com.bridgelabz.onboarding.service.BankInfoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/candidates/{id}/bank-info")
@RequiredArgsConstructor
@Validated
public class BankInfoController {
    private final BankInfoService service;

    @PutMapping
    public ResponseEntity<ResponseDTO<BankInfoDTO>> update(@PathVariable Long id,
                                                           @Valid @RequestBody BankInfoDTO dto) {
        try {
            BankInfoDTO updated = service.updateBankInfo(id, dto);
            return new ResponseEntity<>(new ResponseDTO<>("Bank info updated", updated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error updating bank info", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<BankInfoDTO>> get(@PathVariable Long id) {
        try {
            BankInfoDTO dto = service.getBankInfo(id);
            return new ResponseEntity<>(new ResponseDTO<>("Fetched bank info", dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error fetching bank info", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<BankInfoDTO>> create(@PathVariable Long id,
                                                           @Valid @RequestBody BankInfoDTO dto) {
        try {
            BankInfoDTO created = service.createBankInfo(id, dto);
            return new ResponseEntity<>(new ResponseDTO<>("Bank info created", created), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error creating bank info", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
