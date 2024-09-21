package io.github.muktae.be_codebase.domain.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class KafkaQuestionResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private String query;
        private List<String> choices;
        private int answer;
        private String explanation;

    }

}
