package io.github.muktae.be_codebase.domain.question.domain;

import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workbooks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    private User user;

    private String title;

    private String problem_set;

    public static Question from(User user, String title, String problem_set) {
        return Question.builder()
                .user(user)
                .title(title)
                .problem_set(problem_set)
                .build();
    }
}
