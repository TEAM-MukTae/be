package io.github.muktae.be_codebase.domain.record.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntity;
import io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark;
import io.github.muktae.be_codebase.domain.keyword.domain.Keyword;
import io.github.muktae.be_codebase.domain.record.dto.RecordRequest;
import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "records")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Record extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String transcript;

    private String recordUrl;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords = new ArrayList<>();

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecordSummary> recordSummaries = new ArrayList<>();

    public static Record from(User user, String title, String transcript, String recordUrl) {
        return Record.builder()
                .user(user)
                .title(title)
                .transcript(transcript)
                .recordUrl(recordUrl)
                .build();
    }

}
