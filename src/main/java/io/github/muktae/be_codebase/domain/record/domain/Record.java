package io.github.muktae.be_codebase.domain.record.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntity;
import io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark;
import io.github.muktae.be_codebase.domain.keyword.domain.Keyword;
import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    private User user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String transcript;

    private String recordUrl;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id")
    private RecordSummary recordSummary;

    private boolean isSummarized = false;

    public static Record from(User user, String title, String transcript, String recordUrl) {
        return Record.builder()
                .user(user)
                .title(title)
                .transcript(transcript)
                .recordUrl(recordUrl)
                .recordSummary(null)
                .keywords(new ArrayList<>())
                .bookmarks(new ArrayList<>())
                .build();
    }

    public void changeSummary(RecordSummary recordSummary) {
        this.recordSummary = recordSummary;
        this.isSummarized = true;
    }

    public boolean isSummarized() {
        return isSummarized;
    }

    public void changeTitle(String title) {
        this.title = title;
    }
}
