package io.github.muktae.be_codebase.domain.recordsummary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;
import io.github.muktae.be_codebase.domain.keyword.domain.Keyword;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.record.repository.RecordRepository;
import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import io.github.muktae.be_codebase.domain.recordsummary.dto.RecordSummaryRequest;
import io.github.muktae.be_codebase.domain.recordsummary.repository.RecordSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static io.github.muktae.be_codebase.common.constant.KafkaTopic.PROBLEM_DONE_TOPIC;
import static io.github.muktae.be_codebase.common.constant.KafkaTopic.SUMMARY_DONE_TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordSummaryService {

    private final RecordSummaryRepository recordSummaryRepository;
    private final RecordRepository recordRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = SUMMARY_DONE_TOPIC, groupId = "my-group")
    @Transactional
    public void receiveSummaryFromKafka(String message) {
        System.out.println("message = " + message);
        try {
            RecordSummaryRequest.Create summaryRequest = objectMapper.readValue(message, RecordSummaryRequest.Create.class);

            Record record = recordRepository.findById(summaryRequest.getId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.RECORD_NOT_FOUND));

            if (record.isSummarized()) {
                throw new BusinessException(ErrorCode.ALREADY_SUMMARIZED);
            }

            RecordSummary recordSummary = RecordSummary.from(record, summaryRequest.getSummary());

            summaryRequest.getKeywords()
                    .forEach(keywordName -> Keyword.from(keywordName, record, recordSummary));

            recordSummaryRepository.save(recordSummary);
            record.changeSummary(recordSummary);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error deserializing message: " + e.getMessage());
        }

    }
}
