package com.bridgelabz.onboarding.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.onboarding.service.*;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService excelSvc;
    private final PdfReportService pdfSvc;

    public ReportController(ReportService excelSvc, PdfReportService pdfSvc) {
        this.excelSvc = excelSvc; this.pdfSvc = pdfSvc;
    }

    @GetMapping("/candidates.xlsx")
    public ResponseEntity<InputStreamResource> excel() throws Exception {
        ByteArrayInputStream in = excelSvc.generateExcel();
        HttpHeaders h = new HttpHeaders();
        h.add("Content-Disposition","attachment; filename=candidates.xlsx");
        return ResponseEntity.ok()
                .headers(h)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/candidates.pdf")
    public ResponseEntity<InputStreamResource> pdf() {
        ByteArrayInputStream in = pdfSvc.generatePdf();
        HttpHeaders h = new HttpHeaders();
        h.add("Content-Disposition","attachment; filename=candidates.pdf");
        return ResponseEntity.ok()
                .headers(h)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }
}
