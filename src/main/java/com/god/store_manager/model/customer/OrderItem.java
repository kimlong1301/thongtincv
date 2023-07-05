package com.god.store_manager.model.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order_Items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(name = "orderId")
    private Order order;
    @Column(nullable = false)
    private long productId;
    @Column(nullable = false)
    private long quantity;
    @Column(nullable = false)
    private double price;
}
