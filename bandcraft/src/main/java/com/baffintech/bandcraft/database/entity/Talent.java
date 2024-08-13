package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "talents")
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Size(max = 300)
    @NotNull
    @Column(name = "description", length = 300)
    private String description;

    @Size(max = 500)
    @Column(name = "url_small_photo", length = 500)
    private String urlSmallPhoto;

    @Size(max = 500)
    @Column(name = "url_large_photo", length = 500)
    private String urlLargePhoto;

    @ColumnDefault("0")
    @Column(name = "is_active")
    private Byte isActive;

    // @NotNull
    @Column(name = "date_created")
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

    @ToString.Exclude
    @OneToMany(mappedBy = "talent")
    private Set<MemberTalent> memberTalents = new LinkedHashSet<>();

}