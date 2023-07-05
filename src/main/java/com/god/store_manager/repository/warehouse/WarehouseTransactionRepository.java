package com.god.store_manager.repository.warehouse;
import com.god.store_manager.model.warehouse.WareHouseTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface WarehouseTransactionRepository extends JpaRepository<WareHouseTransaction,Long> {
    Page<WareHouseTransaction> findByTransactionDateBetween(Date dateStart, Date dateEnd, Pageable pageable);
}
