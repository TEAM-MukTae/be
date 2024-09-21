package io.github.muktae.be_codebase.domain.record.service;

import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;
import io.github.muktae.be_codebase.common.uploader.FileUploader;
import io.github.muktae.be_codebase.domain.kafka.KafkaProducer;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.record.dto.RecordRequest;
import io.github.muktae.be_codebase.domain.record.dto.RecordResponse;
import io.github.muktae.be_codebase.domain.record.repository.RecordRepository;
import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import io.github.muktae.be_codebase.domain.recordsummary.repository.RecordSummaryRepository;
import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static io.github.muktae.be_codebase.common.constant.KafkaTopic.SUMMARY_TOPIC;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;
    private final RecordSummaryRepository recordSummaryRepository;
    private final UserRepository userRepository;

    private final FileUploader fileUploader;

    private final KafkaProducer kafkaProducer;

    @Transactional
    public RecordResponse.Create uploadWithKafka(Long userId, RecordRequest.Create recordRequest, MultipartFile file) {

        User user = userRepository.findById((userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        String url = uploadVoice(file);

        Record record = Record.from(user, recordRequest.getTitle(), recordRequest.getText(), url);
        recordRepository.save(record);

        kafkaProducer.sendMessage(SUMMARY_TOPIC, record.getId());

        return RecordResponse.Create.from(record.getId());
    }


    public List<RecordResponse.GetVoiceList> getRecords(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<Record> records = recordRepository.findAllByUser(user);

        return records.stream()
                .map(RecordResponse.GetVoiceList::from)
                .toList();
    }

    private String uploadVoice(MultipartFile voice) {
        return fileUploader.upload(voice, "voice/");
    }

    public RecordResponse.Detail getRecordDetail(Long userId, Long recordId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Record record = recordRepository.findByUserAndId(user, recordId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECORD_NOT_FOUND));


        return RecordResponse.Detail.from(record);
    }

    public void deleteRecord(Long userId, Long recordId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Record record = recordRepository.findByUserAndId(user, recordId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RECORD_NOT_FOUND));

        if (record.isSummarized()) {
            RecordSummary recordSummary = record.getRecordSummary();
            recordSummaryRepository.delete(recordSummary);
        }
        
        String url = record.getRecordUrl();

        recordRepository.deleteById(record.getId());
        fileUploader.delete(url);
    }

}
