package io.github.muktae.be_codebase.domain.user.service;

import io.github.muktae.be_codebase.domain.user.domain.SocialCode;
import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.user.domain.UserRole;
import io.github.muktae.be_codebase.domain.user.exception.UserAccessDeniedException;
import io.github.muktae.be_codebase.domain.user.exception.UserNotFoundException;
import io.github.muktae.be_codebase.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ValidateUserService {

    private final UserRepository userRepository;

    public User validateUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public User validateRegisteredUserByEmail(String email, SocialCode socialCode) {

        return userRepository.findAllByEmail(email).stream()
                .findFirst()
                .orElse(null);
    }

    public User validateAdminUserById(Long userId) {

        User user = validateUserById(userId);
        if (user.getUserRole() == UserRole.ADMIN) {
            return user;
        } else {
            throw new UserAccessDeniedException();
        }
    }

}
