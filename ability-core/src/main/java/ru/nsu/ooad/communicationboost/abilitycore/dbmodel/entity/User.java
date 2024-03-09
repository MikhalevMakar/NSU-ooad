package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import ru.nsu.ooad.communicationboost.abilitycore.enums.Role;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"role", "productRequestList"})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, mappedBy = "user")
    private Set<ProductRequest> productRequestList;

    @PrePersist
    private void initialize() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.role = Role.ROLE_CLIENT;
    }
}
