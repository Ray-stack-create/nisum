package com.example.kafka.config;

import com.example.kafka.model.UserEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_NAME = "user-events";

    @Bean
    public NewTopic userEventsTopic() {
        return new NewTopic(TOPIC_NAME, 1, (short) 1);
    }
}

