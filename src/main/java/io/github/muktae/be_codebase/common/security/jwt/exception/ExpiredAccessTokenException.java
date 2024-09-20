package io.github.muktae.be_codebase.common.security.jwt.exception;


import io.github.muktae.be_codebase.common.exception.ErrorCode;

public class ExpiredAccessTokenException extends TokenException {

    public ExpiredAccessTokenException() {
        super(ErrorCode.EXPIRED_JWT_ACCESS_TOKEN);
    }
}
