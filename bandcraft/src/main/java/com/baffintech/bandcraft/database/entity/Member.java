package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 30)

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Size(max = 60)
    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Size(max = 20)
    @Column(name = "nickname", length = 20)
    private String nickname;

    @Size(max = 1)

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @Size(max = 60)
    @Column(name = "gender_comment", length = 60)
    private String genderComment;

    @Size(max = 20)

    @ColumnDefault("decline")
    @Column(name = "generation", nullable=false, length = 20)
    private String generation;

    @Size(max = 15)
    @NotEmpty
    @Column(name = "phone_cell", nullable = false, length = 15)
    private String phoneCell;

    @Size(max = 15)
    @Column(name = "phone_alt", length = 15)
    private String phoneAlt;

    // email is the username, so not stored here (in users table)

    @Size(max = 150)
    @Column(name = "email_alt", length = 150)
    private String emailAlt;

    @Column(name = "speaks_spanish", columnDefinition = "TINYINT")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private boolean speaksSpanish;

    @Column(name = "speaks_portuguese", columnDefinition = "TINYINT")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private boolean speaksPortuguese;

    @Size(max = 1000)
    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "profile_photo")         // path to
    private String profilePhoto;

    @Size(max = 100)
    @Column(name = "social_media_url", length = 100)
    private String socialMediaUrl;

    @ColumnDefault("-1")

    @Column(name = "registration_status")                               // TODO implement process LATER
    private Byte registrationStatus;

    @Column(name = "date_returning")
    private LocalDate dateReturning;

    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @ColumnDefault("-1")
    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

    @OneToMany(mappedBy = "member")
    private List<MemberTalent> memberTalents = new LinkedList<>();

}
    //private Set<MemberTalent> memberTalents = new LinkedHashSet<>();

    //@OneToMany(mappedBy = "member")
    //private Set<MemberEventType> memberEventTypesSet = new LinkedHashSet<>();
    //private List<MemberEventType> memberEventTypesList = new ArrayList<MemberEventType>();

//    @ColumnDefault("0")
//    @Column(name = "is_active")
//    private boolean isActive;

//    @ColumnDefault("0")
//    @Column(name = "is_active")
//    private boolean isActive;


//    @ColumnDefault("-1")
//    @Column(name = "is_banned")
//    private Byte isBanned;
