package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;   // Jakarta Persistence Query Language
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.Band_Craft.Enums;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

//lombok does the getters and setters
@Setter
@Getter
@Entity //tells there's a db
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")

public class Event {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is indicating to Hibernate that it's doing an auto-increment
    @Column(name = "id")
    private Integer id;

    // FK - eventType_id
    @ToString.Exclude                      // TODO   is this correct?
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventType> eventTypes;

    @Column (name = "event_type_id")       // TODO   is this correct?
    private Integer eventTypeId;

    @Column(name = "start_datetime")
    @NotNull
    private Date startDateTime;         // TODO fix date

    @Column(name = "end_datetime")
    private Date endDateTime;           // TODO fix date

    @Column(name = "location")          // TODO varchar 255
    @NotNull
    private String location;

//    @Column(name = "is_continuous")
//    @NotNull
//    private Boolean isContinuous;
//
//    @Column(name = "is_family_friendly")
//    private Boolean isFamilyFriendly;
//
//    @Column(name = "no_minors_allowed")
//    private Boolean noMinorsAllowed;
//
//    @Column(name = "only_minors_allowed")
//    private Boolean onlyMinorsAllowed;
//
//    @Column(name = "is_gender_specific")
//    private Boolean isGenderSpecific;
//
//    @Column(name = "has_free_food")
//    private Boolean hasFreeFood;
//
//    @Column(name = "is_indoors_has_ac")
//    private Boolean isIndoorsHasAc;
//
//    @Column(name = "is_indoors_has_no_ac")
//    private Boolean isIndoorsHasNoAc;
//
//    @Column(name = "is_outdoors_shade")
//    private Boolean isOutdoorsShade;
//
//    @Column(name = "is_outdoors_no_shade")
//    private Boolean isOutdoorsNoShade;
//
//    @Column(name = "sound_system_provided")         // TODO  varchar 500
//    @NotNull
//    private Boolean soundSystemProvided;

    @Column(name = "sound_system_comments")
    private String soundSystemComments;

    @Column(name = "contact_first_name")            // TODO  varchar 45
    private String contactFirstName;

    @Column(name = "contact_last_name")             // TODO  varchar 45
    private String contactLastName;

    @Column(name = "contact_email")                 // TODO varchar 150
    private String contactEmail;

    @Column(name = "contact_phone_alt")             // TODO varchar 15
    private String contactPhone;

    @Column(name = "contact_phone_cell")            // TODO varchar 15
    @NotNull
    private String contactPhoneCell;

    @Column(name = "comments")                      // TODO varchar 1000
    private String comments;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @Column(name = "last_updated_id")   // defaults to NULL in db if not sent, e.g. not an update    // TODO FK to logged in user
    private Integer lastUpdatedId;

}
