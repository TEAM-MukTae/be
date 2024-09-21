package io.github.muktae.be_codebase.domain.questions.dto;

import io.github.muktae.be_codebase.domain.questions.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class QuestionResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private Long questionId;
        private String query;
        private List<String> choices;
        private int answer;
        private String explanation;

        public static QuestionResponse.Create from(Question question) {
            return Create.builder()
                    .questionId(question.getId())
                    .query(question.getQuery())
                    .choices(question.getChoices())
                    .answer(question.getAnswer())
                    .explanation(question.getExplanation())
                    .build();
        }
    }

}
