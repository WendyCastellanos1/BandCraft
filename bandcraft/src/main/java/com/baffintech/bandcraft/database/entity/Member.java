package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "members", indexes = {
        @Index(name = "user_id_idx", columnList = "user_id"),
        @Index(name = "last_updated_id_idx", columnList = "last_updated_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "id_UNIQUE", columnNames = {"id"})
})
public class Member {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 30)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Size(max = 60)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Size(max = 20)
    @Column(name = "nickname", length = 20)
    private String nickname;

    @Size(max = 1)
    @NotNull
    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @Size(max = 60)
    @Column(name = "gender_comment", length = 60)
    private String genderComment;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("decline")
    @Column(name = "generation", nullable = false, length = 20)
    private String generation;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone_cell", nullable = false, length = 15)
    private String phoneCell;

    @Size(max = 15)
    @Column(name = "phone_alt", length = 15)
    private String phoneAlt;

    // email is the username, so not stored here (in users table)

    @Size(max = 150)
    @Column(name = "email_alt", length = 150)
    private String emailAlt;

    @ColumnDefault("-1")
    @Column(name = "registration_status")       // TODO implement process LATER
    private Byte registrationStatus;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "is_active", nullable = false)
    private Byte isActive;

    @Column(name = "date_returning")
    private LocalDate dateReturning;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "speaks_spanish", nullable = false)
    private Byte speaksSpanish;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "speaks_portuguese", nullable = false)
    private Byte speaksPortuguese;

    @Size(max = 1000)
    @Column(name = "bio", length = 1000)
    private String bio;

    @Size(max = 50)
    @Column(name = "profile_photo", length = 50)
    private String profilePhoto;

    @Size(max = 100)
    @Column(name = "social_media_url", length = 100)
    private String socialMediaUrl;

    @NotNull
    @ColumnDefault("-1")
    @Column(name = "is_banned", nullable = false)
    private Byte isBanned;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @ColumnDefault("-1")
    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

    @OneToMany(mappedBy = "member")
    //private Set<MemberEventType> memberEventTypesSet = new LinkedHashSet<>();
    private List<MemberEventType> memberEventTypesList = new ArrayList<MemberEventType>();

    @OneToMany(mappedBy = "member")
    //private Set<MemberTalent> memberTalents = new LinkedHashSet<>();
    private List<MemberTalent> memberTalents = new LinkedList<>();

}