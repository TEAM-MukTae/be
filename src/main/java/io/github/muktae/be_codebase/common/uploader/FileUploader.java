package io.github.muktae.be_codebase.common.uploader;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileUploader {
    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 1000),
            recover = "recover",
            retryFor = {IOException.class, RuntimeException.class}
    )
    String upload(MultipartFile file, String directory);

    @Recover
    default String recover(Exception e, MultipartFile file){
        throw new RuntimeException("파일 업로드에 실패했습니다.");
    }

    void delete(String url);
}
