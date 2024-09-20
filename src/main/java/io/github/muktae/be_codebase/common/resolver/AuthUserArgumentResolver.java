package io.github.muktae.be_codebase.common.resolver;

import io.github.muktae.be_codebase.common.security.jwt.JwtProperties;
import io.github.muktae.be_codebase.common.security.jwt.JwtTokenInfo;
import io.github.muktae.be_codebase.domain.user.domain.UserRole;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(JwtTokenInfo.class) &&
                parameter.hasParameterAnnotation(AuthUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Claims claims = (Claims) authentication.getPrincipal();
//        Long userId = claims.get(JwtProperties.USER_ID, Long.class);
//        String userRoleKey = claims.get(JwtProperties.USER_ROLE, String.class);
//        UserRole userRole = UserRole.fromKey(userRoleKey);

        return JwtTokenInfo.builder()
                .userId(1L)
                .userRole(UserRole.USER)
                .build();
    }
}