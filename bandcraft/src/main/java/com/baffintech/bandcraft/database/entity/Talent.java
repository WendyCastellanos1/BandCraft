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

    @Size(max = 60)
    @NotNull
    @Column(name = "description", nullable = false, length = 60)
    private String description;

    @Size(max = 500)
    @Column(name = "url_photo1", length = 500)
    private String urlPhoto1;

    @Size(max = 500)
    @Column(name = "url_photo2", length = 500)
    private String urlPhoto2;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "is_active", nullable = false)
    private Byte isActive;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

    @OneToMany(mappedBy = "talent")
    private Set<MemberTalent> memberTalents = new LinkedHashSet<>();

}