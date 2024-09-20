package io.github.muktae.be_codebase.domain.recordsummary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecordSummary is a Querydsl query type for RecordSummary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecordSummary extends EntityPathBase<RecordSummary> {

    private static final long serialVersionUID = -1167642428L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecordSummary recordSummary = new QRecordSummary("recordSummary");

    public final io.github.muktae.be_codebase.common.entity.QBaseEntity _super = new io.github.muktae.be_codebase.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final io.github.muktae.be_codebase.domain.record.domain.QRecord record;

    public final StringPath summaryText = createString("summaryText");

    public QRecordSummary(String variable) {
        this(RecordSummary.class, forVariable(variable), INITS);
    }

    public QRecordSummary(Path<? extends RecordSummary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecordSummary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecordSummary(PathMetadata metadata, PathInits inits) {
        this(RecordSummary.class, metadata, inits);
    }

    public QRecordSummary(Class<? extends RecordSummary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.record = inits.isInitialized("record") ? new io.github.muktae.be_codebase.domain.record.domain.QRecord(forProperty("record"), inits.get("record")) : null;
    }

}

