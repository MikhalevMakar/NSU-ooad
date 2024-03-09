package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_requests")
@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = {"user", "product"})
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_request_id")
    private Long productRequestId;

    @Column(name = "count")
    private int count;

    @Column(name = "accept_status")
    private boolean acceptStatus;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
