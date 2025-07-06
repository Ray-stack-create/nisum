package com.example.actuator.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class HitCounterService {

    private final Counter hitCounter;

    public HitCounterService(MeterRegistry meterRegistry) {
        this.hitCounter = Counter.builder("custom_api_hits_total")
                .description("Total number of API hits")
                .register(meterRegistry);
    }

    public void increment() {
        hitCounter.increment();
    }
}

