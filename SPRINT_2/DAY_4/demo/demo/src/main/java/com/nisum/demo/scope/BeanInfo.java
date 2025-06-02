package com.nisum.demo.scope;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeanInfo {
    private final String id = UUID.randomUUID().toString();
    private final LocalDateTime time = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
