package io.github.muktae.be_codebase.domain.recordsummary.repository;

import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordSummaryRepository extends JpaRepository<RecordSummary, Long> {
}
