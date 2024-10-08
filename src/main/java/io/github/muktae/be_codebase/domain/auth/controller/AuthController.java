package io.github.muktae.be_codebase.domain.auth.controller;

import io.github.muktae.be_codebase.common.resolver.AuthUser;
import io.github.muktae.be_codebase.common.response.SuccessResponse;
import io.github.muktae.be_codebase.common.security.jwt.JwtProvider;
import io.github.muktae.be_codebase.common.security.jwt.JwtTokenInfo;
import io.github.muktae.be_codebase.common.security.jwt.JwtType;
import io.github.muktae.be_codebase.common.utils.HeaderUtils;
import io.github.muktae.be_codebase.domain.auth.dto.ResponseJwtToken;
import io.github.muktae.be_codebase.domain.auth.service.KakaoOAuthService;
import io.github.muktae.be_codebase.domain.auth.service.OAuthService;
import io.github.muktae.be_codebase.domain.user.domain.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService oAuthService;
    private final KakaoOAuthService kakaoOAuthService;
    private final JwtProvider jwtProvider;

    @GetMapping("/test")
    public ResponseEntity<SuccessResponse<String>> loginWithKakao(
    ) {
        String jwtProviderAccessToken = jwtProvider.createAccessToken(1L, UserRole.USER);
        String jwtProviderRefreshToken = jwtProvider.createRefreshToken(1L, UserRole.USER);

        ResponseJwtToken responseJwtToken = ResponseJwtToken.of(jwtProviderAccessToken, jwtProviderRefreshToken);

        return SuccessResponse.of("success")
                .setRefreshToken(responseJwtToken);
    }


    @Operation(summary = "카카오 OAuth2.0 로그인", description = "Authorization code를 받아 카카오 OAuth2.0 로그인을 진행합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카카오 OAuth2.0 로그인 성공"),
            @ApiResponse(
                    responseCode = "400",
                    description = "C001 : 토큰 형식이 Bearer 형식이 아닙니다.",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(
                    responseCode = "404",
                    description = "S007 : Authorization header에 토큰이 비었습니다.",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(
                    responseCode = "500",
                    description = "O002 : 카카오 OAuth 서버와의 통신에 실패했습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/kakao/login")
    public ResponseEntity<SuccessResponse<String>> loginWithKakao(
            @RequestParam("code") String code
    ) {
        log.info(code);
        return SuccessResponse.of("success")
                .setRefreshToken(kakaoOAuthService.login(code));
    }

    @Operation(summary = "JWT Access token 재발급",
            description = "jwt Refresh token을 받아 jwt Access token을 재발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "jwt Access token 재발급 성공"),
            @ApiResponse(
                    responseCode = "400",
                    description = "C001 : Authorization header 값이 JWT 토큰이 아니거나 없습니다.\t\n"
                            + "S002 : JWT Refresh token이 유효하지 않습니다.\t\n"
                            + "S006 : JWT Refresh token이 만료되었습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/refresh")
    public ResponseEntity<SuccessResponse<String>> getAccessToken(HttpServletRequest request) {

        String refreshToken = HeaderUtils.getJwtToken(request, JwtType.REFRESH);
        jwtProvider.validRefreshToken(refreshToken);

        return SuccessResponse.of("success")
                .setAccessToken(jwtProvider.createNewAccessTokenFromRefreshToken(refreshToken));
    }

}
