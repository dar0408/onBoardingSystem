package com.bridgelabz.onboarding.controller;

import com.bridgelabz.onboarding.dto.CandidateDTO;
import com.bridgelabz.onboarding.dto.ResponseDTO;
import com.bridgelabz.onboarding.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService service;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<CandidateDTO>>> getByStatus(@RequestParam(required = false) String status) {
        try {
            List<CandidateDTO> list = (status != null) ? service.getCandidatesByStatus(status) : service.getAllCandidates();
            ResponseDTO<List<CandidateDTO>> response = new ResponseDTO<>("Fetched candidates", list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO<List<CandidateDTO>> errorResponse = new ResponseDTO<>(
                    "Error fetching candidates: " + e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<CandidateDTO>> create(@Valid @RequestBody CandidateDTO dto) {
        try {
            CandidateDTO created = service.createCandidate(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO<>("Candidate created", created));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>("Error creating candidate: " + e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CandidateDTO>> getById(@PathVariable Long id) {
        try {
            CandidateDTO dto = service.getCandidateById(id);
            return new ResponseEntity<>(new ResponseDTO<>("Fetched candidate", dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error fetching candidate: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/count")
    public ResponseEntity<ResponseDTO<Long>> countByStatus(@RequestParam String status) {
        try {
            long count = service.countCandidatesByStatus(status);
            return new ResponseEntity<>(new ResponseDTO<>("Count fetched", count), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error fetching count: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> updateStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            ResponseDTO<String> result = service.updateStatus(id, status);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error updating status: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/notify-offer")
    public ResponseEntity<ResponseDTO<String>> notifyOffer(@PathVariable Long id) {
        try {
            ResponseDTO<String> result = service.notifyJobOffer(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO<>("Error notifying job offer: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
