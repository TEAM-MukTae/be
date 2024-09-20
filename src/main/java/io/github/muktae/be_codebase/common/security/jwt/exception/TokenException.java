package io.github.muktae.be_codebase.common.security.jwt.exception;

import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;


public class TokenException extends BusinessException {

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TokenException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
