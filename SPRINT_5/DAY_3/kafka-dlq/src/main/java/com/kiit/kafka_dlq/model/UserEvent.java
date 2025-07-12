package com.kiit.kafka_dlq.model;

import lombok.Data;

@Data
public class UserEvent {
    private String userId;
    private String action;

}

