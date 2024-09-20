package io.github.muktae.be_codebase.domain.user.service;

import io.github.muktae.be_codebase.domain.auth.domain.Auth;
import io.github.muktae.be_codebase.domain.auth.repository.AuthRepository;
import io.github.muktae.be_codebase.domain.user.domain.SocialCode;
import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    @Transactional
    public User registerWithOAuth(String email, SocialCode socialCode, String refreshToken) {

        User user = userRepository.save(User.create(email, socialCode));
        Auth auth = Auth.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        authRepository.save(auth);
        return user;
    }

    @Transactional
    public void changeNickname(Long userId, String nickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
        user.changeNickname(nickname);
    }

    public String getNickname(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
        return user.getNickName();
    }
}
