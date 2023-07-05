package com.god.store_manager.repository.warehouse;
import com.god.store_manager.model.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
}
