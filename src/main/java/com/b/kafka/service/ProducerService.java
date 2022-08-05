package com.b.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object data) {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, null, System.currentTimeMillis(),
                String.valueOf(data.hashCode()), data);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(producerRecord);
        future.addCallback(result -> {
            log.info("producer send message to {} : {}", result.getProducerRecord().topic(),
                    result.getProducerRecord().value().toString());
        }, ex -> {
            log.info("producer send message fail:{}", ex.getMessage());
        });
    }
}
