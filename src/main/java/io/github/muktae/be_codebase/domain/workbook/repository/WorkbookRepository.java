package io.github.muktae.be_codebase.domain.workbook.repository;

import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.workbook.domain.WorkBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkbookRepository extends JpaRepository<WorkBook, Long>
{
    List<WorkBook> findAllByUser(User user);
    Optional<WorkBook> findByUserAndId(User user, Long questionId);
}
