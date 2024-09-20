package io.github.muktae.be_codebase.domain.user.domain;

import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String key;

    UserRole(String key) {
        this.key = key;
    }

    public static UserRole fromKey(String key) {
        for (UserRole role : UserRole.values()) {
            if (role.getKey().equals(key)) {
                return role;
            }
        }
        throw new BusinessException(ErrorCode.USER_ROLE_ERROR);
    }
}
