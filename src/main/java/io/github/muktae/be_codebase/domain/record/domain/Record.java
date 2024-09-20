package io.github.muktae.be_codebase.domain.record.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntity;
import io.github.muktae.be_codebase.domain.bookmark.domain.Bookmark;
import io.github.muktae.be_codebase.domain.recordsummary.domain.RecordSummary;
import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "records")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<RecordSummary> recordSummaries = new ArrayList<>();

}
