package io.github.muktae.be_codebase.domain.questions.domain;

import io.github.muktae.be_codebase.domain.questions.domain.converter.QuestionChoicesConverter;
import io.github.muktae.be_codebase.domain.questions.dto.KafkaQuestionResponse;
import io.github.muktae.be_codebase.domain.workbook.domain.WorkBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
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

    public static Question from(KafkaQuestionResponse.Create questionDto, WorkBook workbook) {
        Question question = Question.builder()
                .workBook(workbook)
                .query(questionDto.getQuery())
                .choices(questionDto.getChoices())
                .answer(questionDto.getAnswer())
                .explanation(questionDto.getExplanation())
                .build();
        workbook.getQuestions().add(question);
        return question;
    }
}
