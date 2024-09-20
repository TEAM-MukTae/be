package io.github.muktae.be_codebase.common.config;

import io.github.muktae.be_codebase.domain.user.domain.SocialCode;
import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductInitializer {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = User.create("test@naver.com", SocialCode.KAKAO);
        userRepository.save(user);
    }
}