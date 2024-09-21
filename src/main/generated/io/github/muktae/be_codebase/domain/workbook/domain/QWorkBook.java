package io.github.muktae.be_codebase.domain.workbook.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkBook is a Querydsl query type for WorkBook
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkBook extends EntityPathBase<WorkBook> {

    private static final long serialVersionUID = 2104907664L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkBook workBook = new QWorkBook("workBook");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<io.github.muktae.be_codebase.domain.questions.domain.Question, io.github.muktae.be_codebase.domain.questions.domain.QQuestion> questions = this.<io.github.muktae.be_codebase.domain.questions.domain.Question, io.github.muktae.be_codebase.domain.questions.domain.QQuestion>createList("questions", io.github.muktae.be_codebase.domain.questions.domain.Question.class, io.github.muktae.be_codebase.domain.questions.domain.QQuestion.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final io.github.muktae.be_codebase.domain.user.domain.QUser user;

    public QWorkBook(String variable) {
        this(WorkBook.class, forVariable(variable), INITS);
    }

    public QWorkBook(Path<? extends WorkBook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkBook(PathMetadata metadata, PathInits inits) {
        this(WorkBook.class, metadata, inits);
    }

    public QWorkBook(Class<? extends WorkBook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new io.github.muktae.be_codebase.domain.user.domain.QUser(forProperty("user")) : null;
    }

}

