package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "bands", indexes = {
        @Index(name = "lead_member_id_idx", columnList = "lead_member_id"),
        @Index(name = "last_updated_id_idx", columnList = "last_updated_id")
})
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    // TODO make fk / relation to memberId
    @Column(name = "lead_member_id")        // don't think I'll need the nullable=false unless for magic reason
    private Integer leadMemberId;

    @ColumnDefault("0")
    @Column(name = "is_single_use", nullable = false)
    private Byte isSingleUse;

    @ColumnDefault("0")
    @Column(name = "is_active", nullable = false)
    private Byte isActive;

    @Column(name = "date_created", nullable = false)
    private Instant dateCreated;

    @Column(name = "date_updated")
    private Instant dateUpdated;

    @Column(name = "last_updated_id")
    private Integer lastUpdatedId;

    @OneToMany(mappedBy = "band")
    private Set<BandDetail> bandDetails = new LinkedHashSet<>();

}