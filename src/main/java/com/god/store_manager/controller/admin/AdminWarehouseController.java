package com.god.store_manager.controller.admin;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.warehouse.Warehouse;
import com.god.store_manager.service.LocationService;
import com.god.store_manager.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminWarehouseController {
    private final WarehouseService warehouseService;
    private final LocationService locationService;

    @GetMapping("/warehouse")
    public ResponseEntity getAllWarehouses(@RequestParam(required = false) PaginationRequest paginationRequest) {
        return ResponseEntity.ok(warehouseService.getWarehouseWithPagination(paginationRequest));
    }
    @PostMapping("/warehouse/add")
    public ResponseEntity addWareHouse(@RequestBody Warehouse warehouse){
        if(locationService.validateLocation(warehouse.getWardId(),warehouse.getDistrictId(),warehouse.getProvinceId())){
            if(warehouseService.addWarehouse(warehouse)){
                return ResponseEntity.ok(warehouse);
            }
            else {
                return ResponseEntity.status(500).body("Cannot add the warehouse please try again later");
            }
        }
        else{
            return ResponseEntity.status(404).body("Your warehouse location not valid");
        }
    }
    @PostMapping("/warehouse/update")
    public ResponseEntity updateWareHouse(@RequestBody Warehouse warehouse){
        if(locationService.validateLocation(warehouse.getWardId(),warehouse.getDistrictId(),warehouse.getProvinceId())){
            if(warehouseService.updateWarehouse(warehouse)){
                return ResponseEntity.ok(warehouse);
            }
            else {
                return ResponseEntity.status(500).body("Cannot update the warehouse please try again later");
            }
        }
        else{
            return ResponseEntity.status(404).body("Your warehouse location not valid");
        }
    }
    @PostMapping("/warehouse/delete")
    public ResponseEntity deleteWarehouse(@RequestParam long warehouseId){
        if(warehouseService.deleteWarehouseById(warehouseId)){
            return ResponseEntity.ok(warehouseId);
        }
        else {
            return ResponseEntity.status(404).body("Your warehouseId not exist");
        }
    }
    @GetMapping("/warehouse/transaction")
    public ResponseEntity getAllWarehouseTransactions(@RequestParam(required = false) PaginationRequest paginationRequest
    ) {
        return ResponseEntity.ok(warehouseService.getWarehouseTransactionWithPagination(paginationRequest));
    }
    @GetMapping("/warehouse/transaction-by-period")
    public ResponseEntity getAllWarehouseTransactionsByPeriod(@RequestParam(required = false) PaginationRequest paginationRequest,
                                           @RequestBody Date dateStart,
                                           @RequestBody Date dateEnd
    ) {
        return ResponseEntity.ok(warehouseService.getWarehouseTransactionByTimeRangeWithPagination(
                paginationRequest,
                dateStart,
                dateEnd
        ));
    }
}