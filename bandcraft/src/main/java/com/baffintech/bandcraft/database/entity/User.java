package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;   // Jakarta Persistence Query Language
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.Date;

//lombok does the getters and setters
@Setter
@Getter
@Entity // indicates a db
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class User {

    @Id     // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is indicating to Hibernate that it's doing an auto-increment
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

//    @Column(name = "is_admin")
//    private Boolean isAdmin;
//
//    @Column(name = "is_banned")              // only a leader can overturn the ban
//    private Boolean isBanned;                // db defaults to -1

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant dateUpdated;

    // TODO FK to user_id or member_id
    @Column(name = "date_updated_id")
    private Integer dateUpdatedId;

}

