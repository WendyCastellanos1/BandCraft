package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@ToString
@Entity
@Table(name = "band_details", indexes = {
        @Index(name = "band_id_idx", columnList = "band_id"),
        @Index(name = "member_talent_id_idx", columnList = "member_talent_id"),
        @Index(name = "last_updated_id_idx", columnList = "last_updated_id")
})
public class BandDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_talent_id", nullable = false)
    private MemberTalent memberTalent;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

}