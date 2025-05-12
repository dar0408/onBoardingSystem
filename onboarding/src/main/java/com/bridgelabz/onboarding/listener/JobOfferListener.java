package com.bridgelabz.onboarding.listener;

import com.bridgelabz.onboarding.entity.Candidate;
import com.bridgelabz.onboarding.util.EmailTemplateUtil;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import static com.bridgelabz.onboarding.config.RabbitMQConfig.JOB_OFFER_QUEUE;

@Component
@RequiredArgsConstructor
public class JobOfferListener {

    private final JavaMailSender mailSender;

    @RabbitListener(queues = JOB_OFFER_QUEUE)
    public void listen(Candidate candidate) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(candidate.getEmail());
            helper.setSubject("Your Job Offer");
            String html = EmailTemplateUtil.buildJobOfferHtml(candidate);
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
