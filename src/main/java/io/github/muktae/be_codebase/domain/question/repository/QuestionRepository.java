package io.github.muktae.be_codebase.domain.question.repository;

import io.github.muktae.be_codebase.domain.question.domain.Question;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    List<Question> findAllByUser(User user);
    Optional<Question> findByUserAndId(User user, Long questionId);
}
