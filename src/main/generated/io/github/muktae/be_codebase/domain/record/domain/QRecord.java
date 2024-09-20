package io.github.muktae.be_codebase.domain.record.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecord is a Querydsl query type for Record
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = -127995298L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecord record = new QRecord("record");

    public final io.github.muktae.be_codebase.common.entity.QBaseEntity _super = new io.github.muktae.be_codebase.common.entity.QBaseEntity(this);

    public final ListPath<io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark, io.github.muktae.be_codebase.domain.bookmark.domain.QBookmark> bookmarks = this.<io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark, io.github.muktae.be_codebase.domain.bookmark.domain.QBookmark>createList("bookmarks", io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark.class, io.github.muktae.be_codebase.domain.bookmark.domain.QBookmark.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<io.github.muktae.be_codebase.domain.keyword.domain.Keyword, io.github.muktae.be_codebase.domain.keyword.domain.QKeyword> keywords = this.<io.github.muktae.be_codebase.domain.keyword.domain.Keyword, io.github.muktae.be_codebase.domain.keyword.domain.QKeyword>createList("keywords", io.github.muktae.be_codebase.domain.keyword.domain.Keyword.class, io.github.muktae.be_codebase.domain.keyword.domain.QKeyword.class, PathInits.DIRECT2);

    public final io.github.muktae.be_codebase.domain.recordsummary.domain.QRecordSummary recordSummary;

    public final StringPath recordUrl = createString("recordUrl");

    public final StringPath title = createString("title");

    public final StringPath transcript = createString("transcript");

    public final io.github.muktae.be_codebase.domain.user.domain.QUser user;

    public QRecord(String variable) {
        this(Record.class, forVariable(variable), INITS);
    }

    public QRecord(Path<? extends Record> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecord(PathMetadata metadata, PathInits inits) {
        this(Record.class, metadata, inits);
    }

    public QRecord(Class<? extends Record> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recordSummary = inits.isInitialized("recordSummary") ? new io.github.muktae.be_codebase.domain.recordsummary.domain.QRecordSummary(forProperty("recordSummary"), inits.get("recordSummary")) : null;
        this.user = inits.isInitialized("user") ? new io.github.muktae.be_codebase.domain.user.domain.QUser(forProperty("user")) : null;
    }

}

