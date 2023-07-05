package com.god.store_manager.service;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.warehouse.WareHouseTransaction;
import com.god.store_manager.model.warehouse.Warehouse;
import com.god.store_manager.repository.warehouse.WarehouseRepository;
import com.god.store_manager.repository.warehouse.WarehouseTransactionRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
@AllArgsConstructor
@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseTransactionRepository warehouseTransactionRepository;
    public Page<Warehouse> getWarehouseWithPagination(@Nullable PaginationRequest paginationRequest){
        return warehouseRepository.findAll(PaginationRequest.getPageRequest(paginationRequest,""));
    }
    public Page<WareHouseTransaction> getWarehouseTransactionByTimeRangeWithPagination(
            @Nullable PaginationRequest paginationRequest,
            Date dateStart, Date dateEnd
    ){
        return warehouseTransactionRepository.findByTransactionDateBetween(dateStart,dateEnd, PaginationRequest.getPageRequest(paginationRequest,""));
    }

    public boolean addWarehouse(Warehouse warehouse) {
        try {
            warehouseRepository.save(warehouse);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean updateWarehouse(Warehouse warehouse) {
        try {
            warehouseRepository.save(warehouse);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean deleteWarehouseById(long warehouseId) {
        try {
            warehouseRepository.deleteById(warehouseId);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Page<WareHouseTransaction> getWarehouseTransactionWithPagination(@Nullable PaginationRequest paginationRequest) {
        return warehouseTransactionRepository.findAll(PaginationRequest.getPageRequest(paginationRequest,""));
    }
}
