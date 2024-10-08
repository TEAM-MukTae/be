package io.github.muktae.be_codebase.domain.bookmark.domain;

import io.github.muktae.be_codebase.common.entity.BaseEntity;
import io.github.muktae.be_codebase.domain.record.domain.Record;
import io.github.muktae.be_codebase.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookmarks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id")
    private Record record;

}
