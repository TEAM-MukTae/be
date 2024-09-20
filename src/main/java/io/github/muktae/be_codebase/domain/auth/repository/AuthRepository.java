package io.github.muktae.be_codebase.domain.auth.repository;

import io.github.muktae.be_codebase.domain.auth.domain.Auth;
import io.github.muktae.be_codebase.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByUser(User user);
}
