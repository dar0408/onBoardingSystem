package com.bridgelabz.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpProducer otpProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void generateOtpAndSend(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpProducer.sendOtp(email, otp); // Kafka + Email handled via OtpConsumer
    }

    public boolean verifyOtp(String email, String userOtp) {
        String savedOtp = redisTemplate.opsForValue().get(email);
        return userOtp.equals(savedOtp);
    }

    public void clearOtp(String email) {
        redisTemplate.delete(email);
    }
}
