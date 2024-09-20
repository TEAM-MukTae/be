package io.github.muktae.be_codebase.domain.keyword.domain;

import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keywords")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id")
    private RecordSummary recordSummary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id")
    private Record record;

    private String name;

    public static Keyword from(String keywordName, Record record, RecordSummary recordSummary) {
        Keyword keyword = Keyword.builder()
                .name(keywordName)
                .record(record)
                .recordSummary(recordSummary)
                .build();
        recordSummary.getKeywords().add(keyword);
        record.getKeywords().add(keyword);
        return keyword;
    }
}
