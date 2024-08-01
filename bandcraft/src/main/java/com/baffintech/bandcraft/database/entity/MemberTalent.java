package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "member_talents")
public class MemberTalent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "talent_id", nullable = false)
    private Talent talent;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "preference_ranking", nullable = false)
    private Short preferenceRanking;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "can_improv", nullable = false)
    private Byte canImprov;

    @ColumnDefault("0")
    @Column(name = "reads_chord_charts")
    private Byte readsChordCharts;

    @ColumnDefault("0")
    @Column(name = "sight_reads_this_talent")
    private Byte sightReadsThisTalent;

    @ColumnDefault("0")
    @Column(name = "key1_preferred")
    private Byte key1Preferred;

    @ColumnDefault("0")
    @Column(name = "key2_preferred")
    private Byte key2Preferred;

    @ColumnDefault("0")
    @Column(name = "key1_harmony")
    private Byte key1Harmony;

    @ColumnDefault("0")
    @Column(name = "key2_harmony")
    private Byte key2Harmony;

    @ColumnDefault("0")
    @Column(name = "key1_avoid")
    private Byte key1Avoid;

    @ColumnDefault("0")
    @Column(name = "key2_avoid")
    private Byte key2Avoid;

    @ColumnDefault("0")
    @Column(name = "has_processor")
    private Byte hasProcessor;

    @Size(max = 255)
    @Column(name = "processor_comments")
    private String processorComments;

    @ColumnDefault("0")
    @Column(name = "needs_loaner_instrument")
    private Byte needsLoanerInstrument;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

}

//// pre-generated...older
//
//package com.baffintech.bandcraft.database.entity;
//
///* This class represents a junction table between members and talents.
//    Each unique junction has its own attributes.
// */
//
//import jakarta.persistence.*;   // Jakarta Persistence Query Language
//        import lombok.*;
//        import org.example.Band_Craft.Enums;
//
//import java.util.Date;
//import java.util.List;
//
////lombok does the getters and setters
//@Setter
//@Getter
//@Entity //tells there's a db
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "member_talents")
//
//public class MemberTalent {
//
//    @Id //PK
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is indicating to Hibernate that it's doing an auto-increment
//    @Column(name = "id")
//    private Integer id;
//
//    // TODO: Make FK
//    @Column(name = "member_id")                         //  required
//    private Integer memberId;
//
//    // TODO: Make FK
//    @Column(name = "talent_id")                           // required
//    private Integer talentId;
//
//    @Column(name = "preference_ranking")                  // required  (start it at 1, or don't, but consider...)
//    private Short preferenceRanking;                    // TODO use a smaller int, but make it work with db
//
////    @Column(name = "can_improv")
////    private Boolean canImprov;                          // optional
////
////    @Column(name = "reads_chord_charts")
////    private Boolean readsChordCharts;                   // optional
////
////    @Column(name = "sight_reads_this_talent")
////    private Boolean sightReadsThisTalent;                // optional
//
////    @Column(name = "key1_preferred")
////    @Enumerated(EnumType.STRING)
////    private Enums.MusicalKeys key1Preferred;             // optional
////
////    @Column(name = "key2_preferred")
////    @Enumerated(EnumType.STRING)
////    private Enums.MusicalKeys key2Preferred;            // optional
////
////    @Column(name = "key1_harmony")
////    @Enumerated(EnumType.STRING)
////    private Enums.MusicalKeys key1Harmony;
////
////    @Column(name = "key2_harmony")                      // optional
////    @Enumerated(EnumType.STRING)
////    private Enums.MusicalKeys key2Harmony;
////
////    @Column(name = "key1_avoid")
////    @Enumerated(EnumType.STRING)
////    private Enums.MusicalKeys key1Avoid;                  // optional
////
////    @Column(name = "key2_avoid")
////    @Enumerated(EnumType.STRING)
////    private Enums.MusicalKeys key2Avoid;                  // optional
//
////    @Column(name = "has_processor")
////    private Boolean hasProcessor;                       // optional
////
////    @Column(name = "processor_comments")
////    private String processorComments;                   // optional, default is NULL in db
////
////    @Column(name = "needs_loaner_instrument")
////    private Boolean needsLoanerInstrument;              // optional
//
//    @Column(name = "date_created")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateCreated;
//
//    @Column(name = "date_updated")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateUpdated;
//
//    @Column(name = "last_updated_id")   // defaults to NULL in db if not sent, e.g. not an update    // TODO FK to logged in user
//    private Integer lastUpdatedId;
//
//}
