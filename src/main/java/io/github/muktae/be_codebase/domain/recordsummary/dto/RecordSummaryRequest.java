package io.github.muktae.be_codebase.domain.recordsummary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class RecordSummaryRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {

        private Long id;
        private String summary;
        private List<String> keywords;

        @Override
        public String toString() {
            return "Create{" +
                    "id=" + id +
                    ", summary='" + summary + '\'' +
                    ", keywords=" + keywords +
                    '}';
        }
    }
}
