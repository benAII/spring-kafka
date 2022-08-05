package com.b.kafka.controller;

import com.b.kafka.entity.KafkaMessage;
import com.b.kafka.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final ProducerService producerService;

    @PostMapping("/producer")
    public String producer(@RequestParam String topic, @RequestBody KafkaMessage data) {
        producerService.sendMessage(topic, data);
        return "success";
    }
}
