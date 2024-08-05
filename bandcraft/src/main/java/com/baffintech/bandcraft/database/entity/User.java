package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users", indexes = {
        @Index(name = "date_updated_id_idx", columnList = "date_updated_id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "username", nullable = false, length = 200)
    private String username;

    @Size(max = 150)
    @NotNull
    @Column(name = "password", nullable = false, length = 150)
    private String password;

//    @NotNull
//    @Column(name = "is_admin", nullable = false)
//    private Byte isAdmin;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_updated_id")
    private User lastUpdatedId;

//    @OneToMany(mappedBy = "lastUpdatedId")
//    private Set<Event> events = new LinkedHashSet<>();

    // don't think we'll need this bc one to one relationship between user and member, separate only for security reasons
    //    @OneToMany(mappedBy = "user")
    //    private Set<Member> members = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles = new LinkedHashSet<>();

}