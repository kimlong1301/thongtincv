package com.god.store_manager.model.warehouse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String warehouseName;
    @Column()
    private String address;
    @Column(name = "provinceId")
    private long provinceId;
    @Column(name = "districtId")
    private long districtId;
    @Column(name = "wardId")
    private long wardId;
}
