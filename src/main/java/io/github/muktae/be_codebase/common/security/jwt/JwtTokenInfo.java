package io.github.muktae.be_codebase.common.security.jwt;

import io.github.muktae.be_codebase.domain.user.domain.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenInfo {

    private final Long userId;
    private final UserRole userRole;

    @Builder
    public JwtTokenInfo(Long userId, UserRole userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }
}
