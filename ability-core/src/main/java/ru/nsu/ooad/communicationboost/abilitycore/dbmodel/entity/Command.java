package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.nsu.ooad.communicationboost.abilitycore.enums.Role;

@Entity
@Table(name = "commands")
@Getter
@Setter
@EqualsAndHashCode(exclude = "requiredRole")
@NoArgsConstructor
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "command_id")
    private Long commandId;

    @Column(name = "command_url")
    private String commandUrl;

    @Column(name = "command_info")
    private String commandInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "required_role")
    private Role requiredRole;
}
