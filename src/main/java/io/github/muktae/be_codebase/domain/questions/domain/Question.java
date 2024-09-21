package io.github.muktae.be_codebase.domain.questions.domain;

import io.github.muktae.be_codebase.domain.questions.domain.converter.QuestionChoicesConverter;
import io.github.muktae.be_codebase.domain.workbook.domain.WorkBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "questions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "w_id")
    private WorkBook workBook;

    private String query;

    @Convert(converter = QuestionChoicesConverter.class)
    private List<String> choices;

    private int answer;

    private String explanation;

}
