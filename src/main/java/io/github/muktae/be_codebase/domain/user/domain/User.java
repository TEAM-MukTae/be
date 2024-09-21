package io.github.muktae.be_codebase.domain.user.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntity;
import io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.workbook.domain.WorkBook;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String email;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkBook> workBooks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> records = new ArrayList<>();

    public User(String email, String nickName, SocialCode socialCode) {
        this.email = email;
        this.nickName = nickName;
    }

    public static User create(String email, SocialCode socialCode) {
        return User.builder()
                .email(email)
                .name("박철흠")
                .nickName("기본 닉네임")
                .userRole(UserRole.USER)
                .bookmarks(new ArrayList<>())
                .workBooks(new ArrayList<>())
                .records(new ArrayList<>())
                .build();
    }

    public void changeNickname(String nickname) {
        this.nickName = nickname;
    }

}
