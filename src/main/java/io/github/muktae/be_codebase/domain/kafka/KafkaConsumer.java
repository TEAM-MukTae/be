package io.github.muktae.be_codebase.domain.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static io.github.muktae.be_codebase.common.constant.KafkaTopic.PROBLEM_DONE_TOPIC;
import static io.github.muktae.be_codebase.common.constant.KafkaTopic.SUMMARY_DONE_TOPIC;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = PROBLEM_DONE_TOPIC, groupId = "my-group")
    public void listenToProblemDoneSignal(String id) {
        log.info("Received Message: " + id);
    }


    @KafkaListener(topics = SUMMARY_DONE_TOPIC, groupId = "my-group")
    public void listenToSummaryDoneSignal(String id) {
        log.info("Received Message: " + id);
    }
}