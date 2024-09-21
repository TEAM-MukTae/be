package io.github.muktae.be_codebase.domain.workbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WorkbookRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EditTitle {

        private String title;

        public static WorkbookRequest.EditTitle of(String title) {
            return WorkbookRequest.EditTitle.builder()
                    .title(title)
                    .build();
        }

    }
}
