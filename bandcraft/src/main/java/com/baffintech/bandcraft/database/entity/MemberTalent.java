package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "member_talents")

public class MemberTalent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

   //private Integer memberId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id", nullable = false, referencedColumnName = "id")
    private Member member;

     //private Integer talentId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "talent_id", nullable = false)
    private Talent talent;

    // @NotNull
    @ColumnDefault("0")
    @Column(name = "preference_ranking", nullable = false)
    private Byte preferenceRanking;                                 // TODO member numbers talents by preference, LATER

   // @NotNull
    @ColumnDefault("0")
    @Column(name = "can_improv", nullable = false)
    private Byte canImprov;

    @ColumnDefault("0")
    @Column(name = "reads_chord_charts")
    private Byte readsChordCharts;

    @ColumnDefault("0")
    @Column(name = "sight_reads_this_talent")
    private Byte sightReadsThisTalent;

    @ColumnDefault("x")
    @Column(name = "key1_preferred")
    private Character key1Preferred;

    @ColumnDefault("x")
    @Column(name = "key2_preferred")
    private Character key2Preferred;

    @ColumnDefault("x")
    @Column(name = "key1_harmony")
    private Character key1Harmony;

    @ColumnDefault("x")
    @Column(name = "key2_harmony")
    private Character key2Harmony;

    @ColumnDefault("x")
    @Column(name = "key1_avoid")
    private Character key1Avoid;

    @ColumnDefault("x")
    @Column(name = "key2_avoid")
    private Character key2Avoid;

    @ColumnDefault("0")
    @Column(name = "has_processor")
    private Byte hasProcessor;

    @Size(max = 255)
    @Column(name = "processor_comments")
    private String processorComments;

    @ColumnDefault("0")
    @Column(name = "needs_loaner_instrument")
    private Byte needsLoanerInstrument;

    // @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

    @OneToMany(mappedBy = "memberTalent")
    private Set<BandDetail> bandDetails = new LinkedHashSet<>();
    // private List<BandDetail> bandDetailList = new LinkedList<>();    // other option

}