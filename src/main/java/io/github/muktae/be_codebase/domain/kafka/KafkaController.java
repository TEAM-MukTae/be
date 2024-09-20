package io.github.muktae.be_codebase.domain.kafka;

import io.github.muktae.be_codebase.common.uploader.FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static io.github.muktae.be_codebase.common.constant.KafkaTopic.PROBLEM_TOPIC;
import static io.github.muktae.be_codebase.common.constant.KafkaTopic.SUMMARY_TOPIC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class KafkaController {

    private final KafkaProducer producer;
    private final FileUploader fileUploader;

    @GetMapping("/kafkaTest")
    public String test_summary(){
        System.out.println("producer = " + producer);
        producer.sendMessage(SUMMARY_TOPIC,155L);
        return "good";
    }

    @GetMapping("/2")
    public String test_problem(){
        producer.sendMessage(PROBLEM_TOPIC,166L);
        return "good";
    }

    @PostMapping("/file")
    public String test_s3(MultipartFile file){
//        fileUploader.upload(file, "")
        return null;
    }

}