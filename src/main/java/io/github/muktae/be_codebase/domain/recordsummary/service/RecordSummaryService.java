package io.github.muktae.be_codebase.domain.recordsummary.service;

import io.github.muktae.be_codebase.domain.recordsummary.dto.RecordSummaryRequest;
import io.github.muktae.be_codebase.domain.recordsummary.repository.RecordSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static io.github.muktae.be_codebase.common.constant.KafkaTopic.PROBLEM_DONE_TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordSummaryService {

    private final RecordSummaryRepository recordSummaryRepository;

    @KafkaListener(topics = PROBLEM_DONE_TOPIC, groupId = "my-group")
    public void receiveSummaryFromKafka(RecordSummaryRequest.Create summaryRequest) {
        log.info("Received Message: " + summaryRequest);
    }
}
