package io.github.muktae.be_codebase.domain.keyword.domain;

import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keywords")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_summary_id")
    private RecordSummary recordSummary;

    private String name;
}
