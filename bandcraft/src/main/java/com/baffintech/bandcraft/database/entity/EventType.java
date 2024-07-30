package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;   // Jakarta Persistence Query Language
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

//lombok does the getters and setters
@Setter
@Getter
@Entity // indicates a db
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_types")


public class EventType {

    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indicates to Hibernate that it's doing an auto-increment
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="description")
    @NotNull
    private String description;

//    @Column(name="is_active")
//    @NotNull
//    private Short isActive;           // Boolean as tinyint in db

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @Column(name = "last_updated_id")   // defaults to NULL in db if not sent, e.g. not an update    // TODO FK to logged in user
    private Integer lastUpdatedId;

}
