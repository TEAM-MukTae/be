package io.github.muktae.be_codebase.domain.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 454246802L;

    public static final QUser user = new QUser("user");

    public final io.github.muktae.be_codebase.common.entity.QBaseEntityWithUpdate _super = new io.github.muktae.be_codebase.common.entity.QBaseEntityWithUpdate(this);

    public final ListPath<io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark, io.github.muktae.be_codebase.domain.bookmark.domain.QBookmark> bookmarks = this.<io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark, io.github.muktae.be_codebase.domain.bookmark.domain.QBookmark>createList("bookmarks", io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark.class, io.github.muktae.be_codebase.domain.bookmark.domain.QBookmark.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final ListPath<io.github.muktae.be_codebase.domain.record.domain.Record, io.github.muktae.be_codebase.domain.record.domain.QRecord> records = this.<io.github.muktae.be_codebase.domain.record.domain.Record, io.github.muktae.be_codebase.domain.record.domain.QRecord>createList("records", io.github.muktae.be_codebase.domain.record.domain.Record.class, io.github.muktae.be_codebase.domain.record.domain.QRecord.class, PathInits.DIRECT2);

    public final EnumPath<SocialCode> socialCode = createEnum("socialCode", SocialCode.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final EnumPath<UserRole> userRole = createEnum("userRole", UserRole.class);

    public final ListPath<io.github.muktae.be_codebase.domain.workbook.domain.WorkBook, io.github.muktae.be_codebase.domain.workbook.domain.QWorkBook> workBooks = this.<io.github.muktae.be_codebase.domain.workbook.domain.WorkBook, io.github.muktae.be_codebase.domain.workbook.domain.QWorkBook>createList("workBooks", io.github.muktae.be_codebase.domain.workbook.domain.WorkBook.class, io.github.muktae.be_codebase.domain.workbook.domain.QWorkBook.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

