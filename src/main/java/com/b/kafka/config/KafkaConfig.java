package com.b.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.my-topic1}")
    private String myTopic1;

    @Value("${kafka.topic.my-topic2}")
    private String myTopic2;

    /**
     * JSON消息转换器
     *
     * @return
     */
    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public NewTopic myTopic1() {
        return new NewTopic(myTopic1, 2, (short) 1);
    }

    @Bean
    public NewTopic myTopic2() {
        return new NewTopic(myTopic2, 1, (short) 1);
    }
}
