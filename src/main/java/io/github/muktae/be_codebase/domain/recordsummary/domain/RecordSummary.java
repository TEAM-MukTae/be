package io.github.muktae.be_codebase.domain.recordsummary.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntity;
import io.github.muktae.be_codebase.domain.keyword.domain.Keyword;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "record_summaries")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecordSummary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "recordSummary",cascade = CascadeType.ALL, orphanRemoval = true)
    private Record record;

    private String summary;

    @OneToMany(mappedBy = "recordSummary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords;

    public static RecordSummary from(Record record, String summary) {
        return RecordSummary.builder()
                .record(record)
                .summary(summary)
                .keywords(new ArrayList<>())
                .build();
    }
}
