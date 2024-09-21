package io.github.muktae.be_codebase.domain.record.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RecordRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        @NotNull
        private String title;

        @NotNull
        private String text;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EditTitle {

        private String title;

    }
}
