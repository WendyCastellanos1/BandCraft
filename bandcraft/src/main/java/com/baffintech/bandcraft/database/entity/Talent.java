package com.baffintech.bandcraft.database.entity;

// This class represents a look-up value table for talents that people can bring to a band
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
@Table(name = "talents")

public class Talent {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this is indicating to Hibernate that it's doing an auto-increment
    @Column(name = "id")
    private Integer id;

    //FK        TODO:  Is this correct?
    @ToString.Exclude
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberTalent> memberTalents;

    @Column(name = "name")          // required
    private String name;

    @Column(name = "description")   // required
    private String description;

    @Column(name = "url_photo1")    // optional; defaults to NULL in db
    private String urlPhoto1;

    @Column(name = "url_photo2")    // optional; defaults to NULL in db
    private String urlPhoto2;

//    @Column(name = "is_active")     // required
//    @NotNull
//    private Boolean isActive;       // defaults to null in the db

    @Column(name = "date_created")  // required
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;       // TODO: format to get datetime

    @Column(name = "date_updated")   // defaults to NULL in db if not sent, e.g. not an update
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;        // TODO: format to get datetime

   @Column(name = "last_updated_id")   // defaults to NULL in db if not sent, e.g. not an update    // TODO FK to logged in user
   private Integer lastUpdatedId;

}
