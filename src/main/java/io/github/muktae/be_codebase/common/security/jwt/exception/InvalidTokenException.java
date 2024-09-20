package io.github.muktae.be_codebase.common.security.jwt.exception;

import io.github.muktae.be_codebase.common.exception.ErrorCode;

public class InvalidTokenException extends TokenException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

    public InvalidTokenException(Throwable throwable) {
        super(ErrorCode.INVALID_TOKEN, throwable);
    }
}
