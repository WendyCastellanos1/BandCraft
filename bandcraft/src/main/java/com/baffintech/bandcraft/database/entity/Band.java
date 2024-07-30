package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;   // Jakarta Persistence Query Language
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
@Table(name = "bands")

public class Band {

    @Id //this identifies the PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is indicating to Hibernate that it's doing an auto-increment
    @Column(name = "id")
    private Integer id;







    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @Column(name = "last_updated_id")   // defaults to NULL in db if not sent, e.g. not an update    // TODO FK to logged in user
    private Integer lastUpdatedId;

}
