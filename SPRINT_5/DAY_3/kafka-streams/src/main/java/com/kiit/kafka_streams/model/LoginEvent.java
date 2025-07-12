package com.kiit.kafka_streams.model;


public class LoginEvent {
    private String userId;
    private long timestamp;

    public LoginEvent() {}

    public LoginEvent(String userId, long timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
