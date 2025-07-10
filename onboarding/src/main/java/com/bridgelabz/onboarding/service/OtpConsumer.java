package com.bridgelabz.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class OtpConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @KafkaListener(topics = "otp-topic", groupId = "onboarding-group")
    public void consume(String message) {
        String[] parts = message.split(",");
        String email = parts[0];
        String otp = parts[1];

        redisTemplate.opsForValue().set(email, otp, 5, TimeUnit.MINUTES);
        emailService.sendEmail(email, otp);
    }
}
