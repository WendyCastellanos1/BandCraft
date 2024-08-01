package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;   // Jakarta Persistence Query Language
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

//lombok does the getters and setters
@Setter
@Getter
@Entity //tells there's a db
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")

public class Member {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is indicating to Hibernate that it's doing an auto-increment
    @Column(name = "id")
    private Integer id;

    // FK                               // TODO  is this correct?
    @ToString.Exclude
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberTalent> memberTalents;

    @Column(name = "first_name")        // required
    @NotNull                            // varchar 30
    private String firstName;

    @Column(name = "last_name")         // required
    @NotNull                            // varchar 60
    private String lastName;            // if it matches db field name Hibernate figures this out

    @Column(name = "nickname")          // optional
    private String nickname;            // varchar 30

    @Column(name = "gender")             // required (because some events are only for women, for example)
    @NotNull                            // varchar 1
    private String gender;

    @Column(name = "gender_comment")     // optional, field available to any gender choice
    private String genderComment;

//    @Column(name="generation")
//    @Enumerated(EnumType.STRING)
//    private Enums.Generation generation;   // some answer required, though decline is an answer; exists bc some events benefit from generation-specific knowledge

   @Column(name = "phone_cell")             // required for texting, allow bogus numbers in case someone doesn't have a cell
   @NotNull
   private String phoneCell;

    @Column(name = "phone_alt")             // optional
    private String phoneAlt;

    @Column(name = "email")                 // required because band emails go out with links to songs, etc.
    private String email;

    @Column(name = "email_alt")             // optional, but encouraged!
    private String emailAlt;                //optional

//    @Column(name = "registration_status")
//    @Enumerated(EnumType.STRING)
//    private Enums.RegistrationStatus registrationStatus;

//    @Column(name = "is_active")             // leader sets this internally when approved
//   // some answer required, though decline is an answer; exists bc some events benefit from generation-specific knowledg
//    private Boolean isActive;              // defaults to false (0)

//    @Column(name = "date_returning")        //indicates return date for person going on hiatus
//    private Date dateReturning;            // db defaults to Null

//    @Column(name = "speaks_spanish")        //  TODO: note that *enum Language* exists for whatever use
//    private Boolean speaksSpanish;         // db defaults to false (0)
//
//    @Column(name = "speaks_portuguese")     // TODO: note that *enum Language* exists
//    private Boolean speaksPortuguese;      // db defaults to false (0)
//
//    @Column(name = "speaks_other")          // TODO: note that *enum Language* exists
//    private Boolean speaksOther;
//
//    @Column( name = "speaksOtherComment")  // free form entry by member regarding other languages
//    private Boolean speaksOtherComment;   // db defaults to false (0)

    @Column(name = "bio")              // optional, member makes their own bio here when they join
    private String bio;                // db defaults to null

    @Column(name = "profile_photo_url")     // TODO: evaluate if we should store a photo; how to upload, etc.
    private String profilePhotoUrl;         // TODO: varchar 200; put a *default URL* for a generic image in the db

    @Column(name = "social_media_url")      // TODO: varchar 200;
    private String socialMediaUrl;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @Column(name = "last_updated_id")   // defaults to NULL in db if not sent, e.g. not an update    // TODO FK to logged in user
    private Integer lastUpdatedId;


    // FK on last_updated_id TODO: Does this self-join work?  OR are we linking to user_id?
    // select m.id from members m, members mm where m.id = mm.last_updated_id;  // defaults to -1
//    @ManyToOne(cascade={CascadeType.ALL})
//    @JoinColumn(name="last_updated_id")
//    private Member leader;
//
//    @OneToMany(mappedBy="leader")
//    private Set<Member> subordinates = new HashSet<Member>();
}
