package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Table(name = "contexts")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Context {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "context_id")
    private Long contextId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "attempts")
    private int attempts;

    @Column(name = "is_active")
    private boolean isActive;
}
