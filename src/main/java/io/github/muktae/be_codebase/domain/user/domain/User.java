package io.github.muktae.be_codebase.domain.user.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntityWithUpdate;
import io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.workbook.domain.WorkBook;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLRestriction("deleted_at is null")
@Table(name = "user")
public class User extends BaseEntityWithUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_name")
    private String nickName;

    @Enumerated(EnumType.STRING)
    private SocialCode socialCode;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private LocalDateTime deletedAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WorkBook> workBooks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Record> records = new ArrayList<>();

    public User(String email, String nickName, SocialCode socialCode) {
        this.email = email;
        this.nickName = nickName;
        this.socialCode = socialCode;
        this.userRole = UserRole.USER;
    }

    public static User create(String email, SocialCode socialCode) {
        return User.builder()
                .email(email)
                .nickName("기본 닉네임")
                .socialCode(socialCode)
                .userRole(UserRole.USER)
                .build();
    }

    public void changeNickname(String nickname) {
        this.nickName = nickname;
    }

    public void deleteUser() {
        this.deletedAt = LocalDateTime.now();
    }
}
