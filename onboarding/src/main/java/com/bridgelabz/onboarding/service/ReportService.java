package com.bridgelabz.onboarding.service;

import java.io.*;
import java.util.List;
import com.bridgelabz.onboarding.entity.Candidate;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bridgelabz.onboarding.repository.CandidateRepository;

@Service
public class ReportService {
    @Autowired private CandidateRepository repo;

    public ByteArrayInputStream generateExcel() throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            var sheet = wb.createSheet("Candidates");
            var hdr = sheet.createRow(0);
            hdr.createCell(0).setCellValue("ID");
            hdr.createCell(1).setCellValue("Name");
            hdr.createCell(2).setCellValue("Email");
            List<Candidate> list = repo.findAll();
            int rowIdx = 1;
            for (Candidate c : list) {
                var row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(c.getId());
                row.createCell(1).setCellValue(c.getFirstName()+" "+c.getLastName());
                row.createCell(2).setCellValue(c.getEmail());
            }
            wb.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
