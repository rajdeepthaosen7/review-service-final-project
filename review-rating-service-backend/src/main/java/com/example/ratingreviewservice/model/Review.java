package com.example.ratingreviewservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "reviews",
       indexes = {
           @Index(name = "idx_reviews_product_id", columnList = "product_id"),
           @Index(name = "idx_reviews_customer_id", columnList = "customer_id")
       }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_reviews_product"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_reviews_customer"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    private String username;

    @Column(length = 2000)
    private String comment;

    private int rating;

    @Column(name = "created_at", nullable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();
}
