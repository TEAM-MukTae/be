package io.github.muktae.be_codebase.domain.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.github.muktae.be_codebase.common.constant.KafkaTopic.PROBLEM_TOPIC;
import static io.github.muktae.be_codebase.common.constant.KafkaTopic.SUMMARY_TOPIC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
public class KafkaController {

    private final KafkaProducer producer;

    @GetMapping()
    String test_summary(){
        producer.sendMessage(SUMMARY_TOPIC,"a");
        return "good";
    }


    @GetMapping("/2")
    String test_problem(){
        producer.sendMessage(PROBLEM_TOPIC,"a");
        return "good";
    }
}