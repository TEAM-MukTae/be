package io.github.muktae.be_codebase.domain.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendId(String topic, Long id) {
        kafkaTemplate.send(topic, String.valueOf(id));
    }

    public void sendObject(String topic, String jsonObject)
    {
        kafkaTemplate.send(topic, jsonObject);
    }
}