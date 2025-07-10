package com.bridgelabz.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OtpProducer {

    private static final String TOPIC = "otp-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOtp(String email, String otp) {
        String message = email + "," + otp; // Match the format expected by OtpConsumer
        kafkaTemplate.send(TOPIC, message);
    }
}
