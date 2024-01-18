package com.toyoda.product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Integer id;

    private String name;

    private boolean enabled;
}
