package com.oscar.database.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    private String pid;
}
