package io.github.muktae.be_codebase.domain.workbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class KafkaWorkbookRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private List<String> urlList;
        private List<Long> idList;

        public static KafkaWorkbookRequest.Create from(List<String> urlList, List<Long> idList) {
            return KafkaWorkbookRequest.Create.builder()
                    .urlList(urlList)
                    .idList(idList)
                    .build();
        }
    }
}
