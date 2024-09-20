package io.github.muktae.be_codebase.domain.record.repository;


import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByUser(User user);

    Optional<Record> findByUserAndId(User user, Long recordId);

}
