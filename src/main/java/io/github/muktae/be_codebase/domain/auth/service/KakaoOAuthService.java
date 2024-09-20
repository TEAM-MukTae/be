package io.github.muktae.be_codebase.domain.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.muktae.be_codebase.common.exception.BusinessException;
import io.github.muktae.be_codebase.common.exception.ErrorCode;
import io.github.muktae.be_codebase.common.security.jwt.JwtProvider;
import io.github.muktae.be_codebase.domain.auth.domain.Auth;
import io.github.muktae.be_codebase.domain.auth.dto.KakaoOAuthUserProfile;
import io.github.muktae.be_codebase.domain.auth.dto.OAuthAccessToken;
import io.github.muktae.be_codebase.domain.auth.dto.ResponseJwtToken;
import io.github.muktae.be_codebase.domain.auth.exception.OAuthNotFoundException;
import io.github.muktae.be_codebase.domain.auth.properties.KakaoProperties;
import io.github.muktae.be_codebase.domain.auth.repository.AuthRepository;
import io.github.muktae.be_codebase.domain.user.domain.SocialCode;
import io.github.muktae.be_codebase.domain.user.domain.User;
import io.github.muktae.be_codebase.domain.user.service.UserService;
import io.github.muktae.be_codebase.domain.user.service.ValidateUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoOAuthService {

    private final KakaoProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final ValidateUserService validateUserService;
    private final AuthRepository authRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public ResponseJwtToken login(String code) {
        OAuthAccessToken accessToken = getAccessToken(code);
        KakaoOAuthUserProfile oAuthUserProfile = getUserProfile(accessToken.getAccessToken());
        User user = validateUserService.validateRegisteredUserByEmail(
                oAuthUserProfile.getKakaoAccount().getEmail(), SocialCode.KAKAO);

        if (user == null) {
            user = userService.registerWithOAuth(
                    oAuthUserProfile.getKakaoAccount().getEmail(), SocialCode.KAKAO, accessToken.getRefreshToken());
        }

        String jwtProviderAccessToken = jwtProvider.createAccessToken(user.getId(), user.getUserRole());
        log.info(jwtProviderAccessToken);
        String jwtProviderRefreshToken = jwtProvider.createRefreshToken(user.getId(), user.getUserRole());

        return ResponseJwtToken.of(jwtProviderAccessToken, jwtProviderRefreshToken);
    }


    public OAuthAccessToken getAccessToken(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", properties.getApiKey());
        body.add("redirect_uri", properties.getRedirectUri());
        body.add("code", code);
        body.add("client_secret", properties.getSecretKey());

        HttpEntity<MultiValueMap<String, String>> requestToken = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(properties.getTokenUrl(), HttpMethod.POST, requestToken, String.class);
        try {
            return objectMapper.readValue(response.getBody(), OAuthAccessToken.class);
        } catch (JsonProcessingException e) {
            throw new BusinessException(ErrorCode.PARSING_ERROR, e);
        }
    }

    public KakaoOAuthUserProfile getUserProfile(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\"]");


        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> userInfoResponse = restTemplate.exchange(properties.getUserInfoUrl(), HttpMethod.GET,
                httpEntity, String.class);
        try {
            return objectMapper.readValue(userInfoResponse.getBody(), KakaoOAuthUserProfile.class);
        } catch (JsonProcessingException e) {
            throw new BusinessException(ErrorCode.PARSING_ERROR, e);
        }
    }
}