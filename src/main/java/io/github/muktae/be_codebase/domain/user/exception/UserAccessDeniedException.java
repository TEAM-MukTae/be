package io.github.muktae.be_codebase.domain.user.exception;

import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;

public class UserAccessDeniedException extends BusinessException {

    public UserAccessDeniedException() {
        super(ErrorCode.USER_ACCESS_DENIED);
    }

}
