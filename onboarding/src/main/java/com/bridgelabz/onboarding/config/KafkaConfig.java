package com.bridgelabz.onboarding.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic otpTopic() {
        return new NewTopic("otp-topic", 1, (short) 1);
    }
}
