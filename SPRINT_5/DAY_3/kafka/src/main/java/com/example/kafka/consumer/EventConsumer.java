package com.example.kafka.consumer;

import com.example.kafka.model.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @KafkaListener(topics = "user-events", groupId = "user-event-group")
    public void consumeUserEvent(UserEvent event) {
        logger.info("Consumed User Event: {}", event);
    }
}
