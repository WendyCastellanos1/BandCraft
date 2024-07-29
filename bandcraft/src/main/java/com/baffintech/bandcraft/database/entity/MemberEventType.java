package com.baffintech.bandcraft.database.entity;

// this class represents a junction table between members and event_types to map member interest to gigs they would like to be notified for

import jakarta.persistence.*;   // Jakarta Persistence Query Language
import lombok.*;
import java.util.List;

//lombok does the getters and setters
@Setter
@Getter
@Entity //tells there's a db
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_event_types")

public class MemberEventType {

    @Id //this identifies the PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is indicating to Hibernate that it's doing an auto-increment
    @Column(name = "id")
    private Integer id;

}
