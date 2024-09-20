package io.github.muktae.be_codebase.domain.workbook.domain;


import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workbooks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String problemSet;

}
