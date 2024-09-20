package io.github.muktae.be_codebase.domain.record.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class RecordRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Create {

        @NotNull
        private MultipartFile file;

        @NotNull
        private String title;

        @NotNull
        private String text;

    }
}
