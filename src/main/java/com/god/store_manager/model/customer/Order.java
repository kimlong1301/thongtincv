package com.god.store_manager.model.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userId;
    @Column(nullable = false)
    private Date orderDate;
    @Column(nullable = false)
    private long totalAmount;
    @Column()
    private String shippingAddress;
    @Column()
    private String city;
     @Column()
    private long provinceId;
    @Column()
    private long districtId;
    @Column()
    private long wardId;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
