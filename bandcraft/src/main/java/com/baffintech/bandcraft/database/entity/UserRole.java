package com.baffintech.bandcraft.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_roles", indexes = {
        @Index(name = "FK_user_id_idx", columnList = "user_id")
})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 45)
    @NotNull
    @Column(name = "role_name", nullable = false, length = 45)
    private String roleName;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

}