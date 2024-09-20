package io.github.muktae.be_codebase.domain.auth.exception;


import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;

public class OAuthNotFoundException extends BusinessException {

    public OAuthNotFoundException() {
        super(ErrorCode.OAUTH_NOT_FOUND);
    }

}
