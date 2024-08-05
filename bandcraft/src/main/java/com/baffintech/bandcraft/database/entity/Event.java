package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "events", indexes = {
        @Index(name = "event_type_id_idx", columnList = "event_type_id"),
        @Index(name = "last_updated_id_idx", columnList = "last_updated_id")
})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventType eventType;

    @NotNull
    @Column(name = "start_datetime", nullable = false)
    private Instant startDatetime;

    @Column(name = "end_datetime")
    private Instant endDatetime;

    @Size(max = 255)
    @NotNull
    @Column(name = "location", nullable = false)
    private String location;

    @NotNull
    @Column(name = "is_continuous", nullable = false)
    private Byte isContinuous;

    @Column(name = "is_family_friendly")
    private Byte isFamilyFriendly;

    @Column(name = "no_minors_allowed")
    private Byte noMinorsAllowed;

    @Column(name = "only_minors_allowed")
    private Byte onlyMinorsAllowed;

    @Column(name = "is_gender_specific")
    private Byte isGenderSpecific;

    @Column(name = "has_free_food")
    private Byte hasFreeFood;

    @Column(name = "is_indoors_has_ac")
    private Byte isIndoorsHasAc;

    @Column(name = "is_indoors_no_ac")
    private Byte isIndoorsNoAc;

    @Column(name = "is_outdoors_shade")
    private Byte isOutdoorsShade;

    @Column(name = "is_outdoors_no_shade")
    private Byte isOutdoorsNoShade;

    @NotNull
    @Column(name = "sound_system_provided", nullable = false)
    private Byte soundSystemProvided;

    @Size(max = 500)
    @Column(name = "sound_system_comments", length = 500)
    private String soundSystemComments;

    @Size(max = 45)
    @Column(name = "contact_first_name", length = 45)
    private String contactFirstName;

    @Size(max = 45)
    @Column(name = "contact_last_name", length = 45)
    private String contactLastName;

    @Size(max = 60)
    @Column(name = "contact_email", length = 60)
    private String contactEmail;

    @Size(max = 15)
    @Column(name = "contact_phone_alt", length = 15)
    private String contactPhoneAlt;

    @Size(max = 15)
    @NotNull
    @Column(name = "contact_phone_cell", nullable = false, length = 15)
    private String contactPhoneCell;

    @Size(max = 1000)
    @Column(name = "comments", length = 1000)
    private String comments;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_updated_id")
    private User lastUpdatedId;

}