package io.github.muktae.be_codebase.domain.record.dto;

import io.github.muktae.be_codebase.domain.keyword.domain.Keyword;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class RecordResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private Long id;

        public static RecordResponse.Create from(Long id) {
            return Create.builder()
                    .id(id)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetVoiceList {

        private Long id;
        private String title;
        private List<String> keywords;
        private boolean isStarred;

        public static RecordResponse.GetVoiceList from(Record record) {
            return GetVoiceList.builder()
                    .id(record.getId())
                    .title(record.getTitle())
                    .keywords(record.getKeywords().stream().map(Keyword::getName).toList())
                    .isStarred(false)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        private Long id;
        private String voiceUrl;
        private String text;
        private String summary;

        public static RecordResponse.Detail from(Record record) {
            return Detail.builder()
                    .id(record.getId())
                    .voiceUrl(record.getRecordUrl())
                    .text(record.getTranscript())
                    .summary(record.getRecordSummary().getSummary())
                    .build();
        }
    }
}






