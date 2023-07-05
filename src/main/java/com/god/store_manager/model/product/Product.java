package com.god.store_manager.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(name = "product_categoryId")
    private ProductCategory productCategory;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private double price;
    @Column()
    private String description;
}
