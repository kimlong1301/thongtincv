package com.god.store_manager.model.warehouse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WareHouse_Transactions")
public class WareHouseTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouse;
    @Column(nullable = false)
    private boolean transactionType;
    @Column(nullable = false)
    private Date transactionDate;
    @Column(nullable = false,name = "productId")
    private long productId;
    @Column(nullable = false)
    private long quantity;
}
