package com.b.kafka.service;

import com.b.kafka.entity.KafkaMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = {"${kafka.topic.my-topic1}"}, groupId = "group1")
    public void consumeMessage1(ConsumerRecord<String, String> consumerRecord) {
        try {
            KafkaMessage result = objectMapper.readValue(consumerRecord.value(), KafkaMessage.class);
            log.info("Consumer message topics:{}, partition:{},:{}", consumerRecord.topic(), consumerRecord.partition(),
                    result.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = {"${kafka.topic.my-topic2}"}, groupId = "group2")
    public void consumeMessage2(KafkaMessage kafkaMessage) {
        log.info("Consumer message:{}", kafkaMessage.toString());
    }
}
