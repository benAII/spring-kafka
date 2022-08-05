package com.b.kafka.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaMessage {
    private String id;
    private String title;
    private String content;
}
