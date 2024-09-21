package io.github.muktae.be_codebase.domain.question.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.muktae.be_codebase.common.uploader.FileUploader;
import io.github.muktae.be_codebase.domain.kafka.KafkaProducer;
import io.github.muktae.be_codebase.domain.question.domain.Question;
import io.github.muktae.be_codebase.domain.question.dto.QuestionResponse;
import io.github.muktae.be_codebase.domain.question.repository.QuestionRepository;
import io.github.muktae.be_codebase.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService
{
    private final FileUploader fileUploader;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    public Optional<QuestionResponse.Create> uploadWithKafka(List<Long> idList, List<MultipartFile> files)
    {
        try
        {
            List<String> urls = uploadDocuments(files);
            QuestionResponse.Create questionResponse = QuestionResponse.Create
                    .from(urls, idList);

            String jsonObject = objectMapper.writeValueAsString(questionResponse);
            kafkaProducer.sendObject("problem", jsonObject);
            return Optional.of(questionResponse);
        }

        catch (JsonProcessingException e)
        {
            return Optional.empty();
        }
    }

    private List<String> uploadDocuments(List<MultipartFile> files)
    {
        List<String> urls = new ArrayList<>();
        files.forEach(file ->
        {
            String url = uploadDocument(file);
            urls.add(url);
        });

        return urls;
    }

    private String uploadDocument(MultipartFile file) {
        return fileUploader.upload(file, "pdf/");
    }
}
