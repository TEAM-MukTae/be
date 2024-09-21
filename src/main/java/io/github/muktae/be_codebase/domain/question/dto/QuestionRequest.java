package io.github.muktae.be_codebase.domain.question.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

public class QuestionRequest
{
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create
    {
        @NotNull
        private String title;

        @NotNull
        private List<Long> idList;
    }
}
