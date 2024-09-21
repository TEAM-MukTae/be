package io.github.muktae.be_codebase.domain.workbook.dto;

import io.github.muktae.be_codebase.domain.questions.dto.KafkaQuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class KafkaWorkBookResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private Long userId;
        private String title;
        private List<KafkaQuestionResponse.Create> questions;

    }

}
