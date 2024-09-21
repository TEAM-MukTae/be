package io.github.muktae.be_codebase.domain.workbook.dto;

import io.github.muktae.be_codebase.domain.questions.dto.QuestionResponse;
import io.github.muktae.be_codebase.domain.workbook.domain.WorkBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class WorkbookResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private Long workbookId;
        private String title;

        public static WorkbookResponse.Create from(WorkBook workBook) {
            return Create.builder()
                    .workbookId(workBook.getId())
                    .title(workBook.getTitle())
                    .build();
        }

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        private String title;
        private List<QuestionResponse.Create> questions;

        public static WorkbookResponse.Detail from(WorkBook workBook) {
            return Detail.builder()
                    .title(workBook.getTitle())
                    .questions(workBook.getQuestions()
                            .stream()
                            .map(QuestionResponse.Create::from)
                            .toList())
                    .build();
        }

    }

}
