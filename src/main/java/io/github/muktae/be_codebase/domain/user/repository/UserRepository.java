package io.github.muktae.be_codebase.domain.user.repository;

import io.github.muktae.be_codebase.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmail(String email);

}
