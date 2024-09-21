package io.github.muktae.be_codebase.domain.question.dto;

import io.github.muktae.be_codebase.domain.record.dto.RecordResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;

public class QuestionResponse
{
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private List<String> urlList;
        private List<Long> idList;

        public static QuestionResponse.Create from(List<String> urlList, List<Long> idList) {
            return Create.builder()
                    .urlList(urlList)
                    .idList(idList)
                    .build();
        }
    }
}
