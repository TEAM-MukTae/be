package io.github.muktae.be_codebase.common.response;

import io.github.muktae.be_codebase.domain.auth.dto.ResponseJwtToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {

    private final boolean status = true;

    private T data;

    public static <T> SuccessResponse<T> of(T data) {
        SuccessResponse<T> successResponse = new SuccessResponse<>();
        successResponse.data = data;

        return successResponse;
    }

    public ResponseEntity<SuccessResponse<T>> setStatus(HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(this);
    }

    public ResponseEntity<SuccessResponse<T>> setAccessToken(String tokens) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", tokens)
                .body(this);
    }


    public ResponseEntity<SuccessResponse<T>> setRefreshToken(ResponseJwtToken tokens) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", tokens.getAccessToken())
                .header("Set-Cookie", "refreshToken=" + tokens.getRefreshToken() + ";" +
                        " Path=/; HttpOnly; SameSite=Strict; Secure;")
                .body(this);
    }

}
