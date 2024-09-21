package io.github.muktae.be_codebase.domain.workbook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;
import io.github.muktae.be_codebase.common.uploader.FileUploader;
import io.github.muktae.be_codebase.domain.kafka.KafkaProducer;
import io.github.muktae.be_codebase.domain.questions.domain.Question;
import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.user.repository.UserRepository;
import io.github.muktae.be_codebase.domain.workbook.domain.WorkBook;
import io.github.muktae.be_codebase.domain.workbook.dto.KafkaWorkBookResponse;
import io.github.muktae.be_codebase.domain.workbook.dto.QuestionResponse;
import io.github.muktae.be_codebase.domain.workbook.repository.WorkbookRepository;
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
public class WorkbookService {

    private final UserRepository userRepository;
    private final WorkbookRepository workbookRepository;

    private final KafkaProducer kafkaProducer;
    private final FileUploader fileUploader;
    private final ObjectMapper objectMapper;

    public void uploadWithKafka(List<Long> idList, List<MultipartFile> files) {

        try {
            List<String> urls = uploadPdf(files);
            String jsonObject = objectMapper.writeValueAsString(QuestionResponse.Create.from(urls, idList));

            System.out.println("jsonObject = " + jsonObject);

            kafkaProducer.sendObject("problem", jsonObject);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error deserializing message: " + e.getMessage());
        }

    }

    @KafkaListener(topics = PROBLEM_DONE_TOPIC, groupId = "my-group")
    @Transactional
    public void receiveQuestionFromKafka(String message) {

        System.out.println("message = " + message);

        try {
            KafkaWorkBookResponse.Create receivedWorkbookFromKafka =
                    objectMapper.readValue(message, KafkaWorkBookResponse.Create.class);

            User user = userRepository.findById(receivedWorkbookFromKafka.getUserId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

            WorkBook createdWorkbookEntity = WorkBook.from(user, receivedWorkbookFromKafka.getTitle());

            receivedWorkbookFromKafka.getQuestions()
                    .forEach(question -> Question.from(question, createdWorkbookEntity));

            workbookRepository.save(createdWorkbookEntity);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error deserializing message: " + e.getMessage());
        }

    }


    private List<String> uploadPdf(List<MultipartFile> files) {
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
