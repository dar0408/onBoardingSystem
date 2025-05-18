package com.bridgelabz.onboarding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.onboarding.service.PdfReportService;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final PdfReportService pdfSvc;

    @GetMapping("/candidates.pdf")
    public ResponseEntity<InputStreamResource> downloadFullReport() {
        ByteArrayInputStream in = pdfSvc.generatePdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=full_candidates_report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }
}
