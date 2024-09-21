package io.github.muktae.be_codebase.domain.workbook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.muktae.be_codebase.common.uploader.FileUploader;
import io.github.muktae.be_codebase.domain.kafka.KafkaProducer;
import io.github.muktae.be_codebase.domain.workbook.dto.QuestionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static io.github.muktae.be_codebase.common.constant.KafkaTopic.PROBLEM_DONE_TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final FileUploader fileUploader;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    public QuestionResponse.Create uploadWithKafka(List<Long> idList, List<MultipartFile> files) {

        QuestionResponse.Create questionResponse = null;

        try {

            List<String> urls = uploadDocuments(files);
            questionResponse = QuestionResponse.Create.from(urls, idList);

            String jsonObject = objectMapper.writeValueAsString(questionResponse);
            System.out.println("jsonObject = " + jsonObject);
            kafkaProducer.sendObject("problem", jsonObject);

            return questionResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error deserializing message: " + e.getMessage());
        }

        return questionResponse;
    }

    @KafkaListener(topics = PROBLEM_DONE_TOPIC, groupId = "my-group")
    @Transactional
    public void receiveSummaryFromKafka(String message) {
        System.out.println("message = " + message);
        try {
            QuestionResponse.Create summaryRequest = objectMapper.readValue(message, QuestionResponse.Create.class);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error deserializing message: " + e.getMessage());
        }

    }


    private List<String> uploadDocuments(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        files.forEach(file -> {
            String url = uploadDocument(file);
            urls.add(url);
        });

        return urls;
    }

    private String uploadDocument(MultipartFile file) {
        return fileUploader.upload(file, "pdf/");
    }
}
