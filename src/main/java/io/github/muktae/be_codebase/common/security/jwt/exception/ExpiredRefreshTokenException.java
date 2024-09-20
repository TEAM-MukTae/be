package io.github.muktae.be_codebase.common.security.jwt.exception;


import io.github.muktae.be_codebase.common.exception.ErrorCode;

public class ExpiredRefreshTokenException extends TokenException {
    public ExpiredRefreshTokenException() {
        super(ErrorCode.EXPIRED_JWT_REFRESH_TOKEN);
    }

    public ExpiredRefreshTokenException(Throwable throwable) {
        super(ErrorCode.EXPIRED_JWT_REFRESH_TOKEN, throwable);
    }
}
