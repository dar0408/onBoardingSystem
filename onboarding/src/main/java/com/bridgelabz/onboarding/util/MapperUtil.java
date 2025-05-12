package com.bridgelabz.onboarding.util;

import com.bridgelabz.onboarding.dto.*;
import com.bridgelabz.onboarding.entity.*;

public class MapperUtil {
    // Candidate mapping
    public static CandidateDTO toDTO(Candidate c) {
        return CandidateDTO.builder()
                .id(c.getId())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .email(c.getEmail())
                .phone(c.getPhone())
                .status(c.getStatus())
                .appliedDate(c.getAppliedDate())
                .onboardedDate(c.getOnboardedDate())
                .createdBy(c.getCreatedBy())
                .createdDate(c.getCreatedDate())
                .lastModifiedBy(c.getLastModifiedBy())
                .lastModifiedDate(c.getLastModifiedDate())
                .build();
    }

    public static Candidate toEntity(CandidateDTO c) {
        return Candidate.builder()
                .id(c.getId())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .email(c.getEmail())
                .phone(c.getPhone())
                .status(c.getStatus())
                .appliedDate(c.getAppliedDate())
                .onboardedDate(c.getOnboardedDate())
                .build();
    }

    // PersonalInfo mapping
    public static PersonalInfoDTO toDTO(PersonalInfo p) {
        return PersonalInfoDTO.builder()
                .candidateId(p.getCandidateId())
                .address(p.getAddress())
                .fatherName(p.getFatherName())
                .motherName(p.getMotherName())
                .build();
    }

    public static PersonalInfo toEntity(PersonalInfoDTO p) {
        return PersonalInfo.builder()
                .candidateId(p.getCandidateId())
                .address(p.getAddress())
                .fatherName(p.getFatherName())
                .motherName(p.getMotherName())
                .build();
    }

    // BankInfo mapping
    public static BankInfoDTO toDTO(BankInfo b) {
        return BankInfoDTO.builder()
                .candidateId(b.getCandidateId())
                .bankName(b.getBankName())
                .accountNumber(b.getAccountNumber())
                .ifscCode(b.getIfscCode())
                .build();
    }

    public static BankInfo toEntity(BankInfoDTO b) {
        return BankInfo.builder()
                .candidateId(b.getCandidateId())
                .bankName(b.getBankName())
                .accountNumber(b.getAccountNumber())
                .ifscCode(b.getIfscCode())
                .build();
    }

    // EducationalInfo mapping
    public static EducationalInfoDTO toDTO(EducationalInfo e) {
        return EducationalInfoDTO.builder()
                .candidateId(e.getCandidateId())
                .highestDegree(e.getHighestDegree())
                .university(e.getUniversity())
                .percentage(e.getPercentage())
                .build();
    }

    public static EducationalInfo toEntity(EducationalInfoDTO e) {
        return EducationalInfo.builder()
                .candidateId(e.getCandidateId())
                .highestDegree(e.getHighestDegree())
                .university(e.getUniversity())
                .percentage(e.getPercentage())
                .build();
    }

    // Document mapping
    public static DocumentDTO toDTO(Document d) {
        return DocumentDTO.builder()
                .id(d.getId())
                .candidateId(d.getCandidateId())
                .fileName(d.getFileName())
                .fileType(d.getFileType())
                .verified(d.isVerified())
                .build();
    }

    public static Document toEntity(DocumentDTO d) {
        return Document.builder()
                .id(d.getId())
                .candidateId(d.getCandidateId())
                .fileName(d.getFileName())
                .fileType(null) // fileType/path set elsewhere when uploading
                .filePath(null)
                .verified(d.isVerified())
                .build();
    }
}