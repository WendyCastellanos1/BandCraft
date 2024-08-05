package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event_types", indexes = {
        @Index(name = "last_updated_id_idx", columnList = "last_updated_id")
})
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "is_active", nullable = false)
    private Byte isActive;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @ColumnDefault("0")
    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

    @OneToMany(mappedBy = "eventType")
    // private Set<Event> events = new LinkedHashSet<>();
    private List<Event> Events = new ArrayList<>();     // list of events with given event type

    @OneToMany(mappedBy = "eventType")
    private Set<MemberEventType> memberEventTypes = new LinkedHashSet<>();  // set of preferred event types by member

}