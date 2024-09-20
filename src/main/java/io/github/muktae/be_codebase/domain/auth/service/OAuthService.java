package io.github.muktae.be_codebase.domain.auth.service;

import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.user.service.ValidateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OAuthService {

    private final KakaoOAuthService kakaoOAuthService;
    private final ValidateUserService validateUserService;


    @Transactional
    public void deleteAccount(Long userId) {
        User user = validateUserService.validateUserById(userId);
        kakaoOAuthService.deleteAccount(user);

    }
}
