package com.kiit.kafka_dlq.listener;

import com.kiit.kafka_dlq.model.UserEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    @KafkaListener(topics = "user-events", groupId = "user-event-consumers")
    public void consume(UserEvent event) {
        System.out.println("Received Event: " + event.getUserId() + " - " + event.getAction());

        // Simulate failure for bad messages
        if (StringUtils.isBlank(event.getUserId()) || StringUtils.isBlank(event.getAction())) {
            throw new RuntimeException("Invalid message: userId or action is missing");
        }

        // Process the valid message
        System.out.println("Processed user event for: " + event.getUserId());
    }
}

