package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createDate;

//    @Column(name = "date_created")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateCreated;
//
//    @Column(name = "date_updated")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateUpdated;
//
//    @Column(name = "last_updated_id")   // defaults to NULL in db if not sent, e.g. not an update    // TODO FK to logged in user
//    private Integer lastUpdatedId;

}
