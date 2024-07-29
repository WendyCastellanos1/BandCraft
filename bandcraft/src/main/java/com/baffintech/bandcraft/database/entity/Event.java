package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;   // Jakarta Persistence Query Language
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
    @ToString.Exclude
    @OneToMany(mappedBy = "event_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventType> eventTypes;

    @Column (name = "event_type_id")
    private Integer eventTypeId;

    @Column(name = "start_datetime")
    private Date startDateTime;

    @Column(name = "end_datetime")
    private Date endDateTime;

    @Column(name = "location")
    private String location;

    @Column(name = "is_continuous")
    private Boolean isContinuous;

    @Column(name = "is_family_friendly")
    private Boolean isFamilyFriendly;

    @Column(name = "no_minors_allowed")
    private Boolean noMinorsAllowed;

    @Column(name = "only_minors_allowed")
    private Boolean onlyMinorsAllowed;

    @Column(name = "is_gender_specific")
    private Boolean isGenderSpecific;

    @Column(name = "has_free_food")
    private Boolean hasFreeFood;

    @Column(name = "is_indoors_has_ac")
    private Boolean isIndoorsHasAc;

    @Column(name = "is_indoors_has_no_ac")
    private Boolean isIndoorsHasNoAc;

    @Column(name = "is_outdoors_shade")
    private Boolean isOutdoorsShade;

    @Column(name = "is_outdoors_no_shade")
    private Boolean isOutdoorsNoShade;

    @Column(name = "sound_system_provided")
    private Boolean soundSystemProvided;

    @Column(name = "sound_system_comments")
    private String soundSystemComments;

    @Column(name = "contact_first_name")
    private String contactFirstName;

    @Column(name = "contact_last_name")
    private String contactLastName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone_alt")
    private String contactPhone;

    @Column(name = "contact_phone_cell")
    private String contactPhoneCell;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "date_updated")
    private String dateUpdated;

    @Column(name = "last_updated_id")
    private String lastUpdatedId;

}
