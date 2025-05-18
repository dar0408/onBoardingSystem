package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.dto.BankInfoDTO;
import com.bridgelabz.onboarding.dto.DocumentDTO;
import com.bridgelabz.onboarding.dto.EducationalInfoDTO;
import com.bridgelabz.onboarding.dto.PersonalInfoDTO;
import com.bridgelabz.onboarding.entity.Candidate;
import com.bridgelabz.onboarding.repository.CandidateRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfReportService {

    private final CandidateRepository candidateRepo;
    private final PersonalInfoService personalInfoService;
    private final BankInfoService bankInfoService;
    private final EducationalInfoService educationalInfoService;
    private final DocumentService documentService;

    public ByteArrayInputStream generatePdf() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc =
                new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document doc = new Document(pdfDoc, PageSize.A4);
        doc.setMargins(20, 20, 20, 20);

        doc.add(new Paragraph("Full Candidate Report")
                .setBold().setFontSize(18));
        doc.add(new Paragraph("\n"));

        List<Candidate> candidates = candidateRepo.findAll();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Candidate c : candidates) {
            doc.add(new Paragraph("Candidate: " + c.getFirstName() + " " + c.getLastName())
                    .setBold().setFontSize(14));
            doc.add(new Paragraph("ID: " + c.getId() + "    Email: " + c.getEmail())
                    .setItalic().setFontSize(10));
            doc.add(new Paragraph("\n"));

            PersonalInfoDTO pi = personalInfoService.getPersonalInfo(c.getId());
            Table t1 = new Table(UnitValue.createPercentArray(new float[]{3, 7}))
                    .useAllAvailableWidth();
            t1.addHeaderCell("Field");
            t1.addHeaderCell("Value");
            t1.addCell("Father Name");
            t1.addCell(pi.getFatherName());
            t1.addCell("Mother Name");
            t1.addCell(pi.getMotherName());
            t1.addCell("Address");
            t1.addCell(pi.getAddress());
            doc.add(new Paragraph("Personal Information").setBold());
            doc.add(t1);
            doc.add(new Paragraph("\n"));

            BankInfoDTO bi = bankInfoService.getBankInfo(c.getId());
            Table t2 = new Table(UnitValue.createPercentArray(new float[]{3, 7}))
                    .useAllAvailableWidth();
            t2.addHeaderCell("Field");
            t2.addHeaderCell("Value");
            t2.addCell("Bank Name");
            t2.addCell(bi.getBankName());
            t2.addCell("Account No");
            t2.addCell(bi.getAccountNumber());
            t2.addCell("IFSC");
            t2.addCell(bi.getIfscCode());
            doc.add(new Paragraph("Bank Information").setBold());
            doc.add(t2);
            doc.add(new Paragraph("\n"));

            EducationalInfoDTO ei = educationalInfoService.getEducationalInfo(c.getId());
            Table t3 = new Table(UnitValue.createPercentArray(new float[]{3, 7}))
                    .useAllAvailableWidth();
            t3.addHeaderCell("Field");
            t3.addHeaderCell("Value");
            t3.addCell("Highest Degree");
            t3.addCell(ei.getHighestDegree());
            t3.addCell("Institution");
            t3.addCell(ei.getUniversity());
            t3.addCell("Percentage");
            t3.addCell(String.valueOf(ei.getPercentage()));
            doc.add(new Paragraph("Educational Information").setBold());
            doc.add(t3);
            doc.add(new Paragraph("\n"));

            List<DocumentDTO> docs = documentService.listDocuments(c.getId());
            Table t4 = new Table(UnitValue.createPercentArray(new float[]{4, 6}))
                    .useAllAvailableWidth();
            t4.addHeaderCell("Document Type");
            t4.addHeaderCell("File Name");
            for (DocumentDTO d : docs) {
                t4.addCell(d.getFileType());
                t4.addCell(d.getFileName());
            }
            doc.add(new Paragraph("Uploaded Documents").setBold());
            doc.add(t4);

            doc.add(new AreaBreak());
        }

        doc.close();
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
