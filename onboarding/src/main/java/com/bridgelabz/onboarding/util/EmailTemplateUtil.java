package com.bridgelabz.onboarding.util;

import com.bridgelabz.onboarding.dto.CandidateDTO;
import com.bridgelabz.onboarding.entity.Candidate;

import java.time.format.DateTimeFormatter;

public class EmailTemplateUtil {
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
                .build();
    }

    public static String buildJobOfferHtml(Candidate c) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = c.getAppliedDate() != null ? c.getAppliedDate().format(fmt) : "N/A";
        return
                "<!DOCTYPE html>" +
                        "<html lang='en'>" +
                        "<head>" +
                        "    <meta charset='UTF-8'>" +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                        "    <title>Offer Letter</title>" +
                        "</head>" +
                        "<body style='margin: 0; padding: 0; background-color: #f4f4f4; font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif;'>" +
                        "    <div style='max-width: 600px; margin: 40px auto; background-color: #ffffff; padding: 40px; border-radius: 10px; box-shadow: 0 8px 16px rgba(0,0,0,0.1);'>" +
                        "        <div style='text-align: center; margin-bottom: 30px;'>" +
                        "            <h1 style='color: #2c3e50; font-size: 28px;'>🎉🍾🎉 Congratulations " + c.getFirstName() + "!</h1>" +
                        "        </div>" +
                        "        <p style='font-size: 16px; color: #333333; line-height: 1.6;'>" +
                        "            We are thrilled to offer you the position you applied for on <strong>" + date + "</strong>." +
                        "        </p>" +
                        "        <p style='font-size: 16px; color: #333333; line-height: 1.6;'>" +
                        "            After thoroughly reviewing your qualifications and experience, we are confident that you will make an excellent addition to our team." +
                        "        </p>" +
                        "        <p style='font-size: 16px; color: #333333; line-height: 1.6;'>" +
                        "            You will soon receive a formal offer letter with detailed information and next steps." +
                        "        </p>" +
                        "        <p style='font-size: 16px; color: #333333; line-height: 1.6;'>" +
                        "            If you have any questions or need further clarification, feel free to contact us anytime." +
                        "        </p>" +
                        "        <p style='font-size: 16px; color: #333333; margin-top: 30px;'>Welcome aboard!</p>" +
                        "        <p style='font-size: 16px; color: #333333;'>Best regards,<br><strong>The HR Team</strong></p>" +
                        "        <hr style='margin: 30px 0; border: none; border-top: 1px solid #ddd;'>" +
                        "        <p style='text-align: center; font-size: 12px; color: #888888;'>© " + java.time.Year.now() + " Your Company Name. All rights reserved.</p>" +
                        "    </div>" +
                        "</body>" +
                        "</html>";


    }
}
