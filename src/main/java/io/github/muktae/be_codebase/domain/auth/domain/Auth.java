package io.github.muktae.be_codebase.domain.auth.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntity;
import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String refreshToken;

    private String sub;

    private String idToken;

    @Builder
    public Auth(User user, String refreshToken, String sub, String idToken) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.sub = sub;
        this.idToken = idToken;
    }

    public static Auth create(User user, String refreshToken) {
        return Auth.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void update(String idToken, String refreshToken) {
        this.idToken = idToken;
        this.refreshToken = refreshToken;
    }
}
