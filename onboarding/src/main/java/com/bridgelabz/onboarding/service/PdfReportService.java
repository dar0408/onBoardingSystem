package com.bridgelabz.onboarding.service;

import java.io.*;
import java.util.List;
import com.bridgelabz.onboarding.entity.Candidate;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.onboarding.repository.CandidateRepository;

@Service
public class PdfReportService {
    @Autowired private CandidateRepository repo;

    public ByteArrayInputStream generatePdf() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        Document doc = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));
        doc.add(new Paragraph("Candidate Report").setBold().setFontSize(16));
        Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        table.addHeaderCell("ID"); table.addHeaderCell("Name"); table.addHeaderCell("Email");
        List<Candidate> list = repo.findAll();
        for (Candidate c : list) {
            table.addCell(c.getId().toString());
            table.addCell(c.getFirstName()+" "+c.getLastName());
            table.addCell(c.getEmail());
        }
        doc.add(table);
        doc.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
