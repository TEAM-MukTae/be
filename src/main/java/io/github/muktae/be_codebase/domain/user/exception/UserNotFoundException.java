package io.github.muktae.be_codebase.domain.user.exception;

import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
