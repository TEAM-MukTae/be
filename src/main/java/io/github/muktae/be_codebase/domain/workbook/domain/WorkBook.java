package io.github.muktae.be_codebase.domain.workbook.domain;


import io.github.muktae.be_codebase.domain.questions.domain.Question;
import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workbooks")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    private User user;

    private String title;

    @OneToMany(mappedBy = "workBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public static WorkBook from(User user, String title) {
        return WorkBook.builder()
                .user(user)
                .title(title)
                .questions(new ArrayList<>())
                .build();
    }

}
