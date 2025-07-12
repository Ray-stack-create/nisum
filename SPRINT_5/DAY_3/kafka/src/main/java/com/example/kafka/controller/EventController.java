package com.example.kafka.controller;


import com.example.kafka.model.UserEvent;
import com.example.kafka.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventProducer eventProducer;

    @PostMapping
    public ResponseEntity<String> postUserEvent(@RequestBody UserEvent event) {
        eventProducer.sendUserEvent(event);
        return new ResponseEntity<>("User event published to Kafka", HttpStatus.OK);
    }
}
