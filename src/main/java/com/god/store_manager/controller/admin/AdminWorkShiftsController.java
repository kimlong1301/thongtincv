package com.god.store_manager.controller.admin;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.employee.Employee;
import com.god.store_manager.model.employee.WorkShift;
import com.god.store_manager.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminWorkShiftsController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/workshifts")
    public Page<WorkShift> getListWorkShiftByEmployeeIdWithPagination(
            @RequestParam(required = false) PaginationRequest paginationRequest,
            @RequestParam long workShiftId){
        return employeeService.getListWorkShiftByEmployeeIdWithPaginate(paginationRequest,workShiftId);
    }
    @GetMapping("/workshifts/info")
    public ResponseEntity getWorkShiftInfo(@RequestParam long workShiftId){
        Optional<WorkShift> workShift = employeeService.getWorkShiftById(workShiftId);
        if(workShift.isPresent()){
            return ResponseEntity.ok(workShift.get());
        }
        else {
            return ResponseEntity.status(404).body("employee not exist, please check employeeId and try again");
        }
    }
    @PostMapping("/workshifts/update")
    public ResponseEntity updateWorkShiftInfo(@RequestBody WorkShift workShift){
        try {
            employeeService.updateWorkShift(workShift);
            return ResponseEntity.ok(workShift);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping("/workshifts/add")
    public ResponseEntity addWorkShiftInfo(@RequestBody WorkShift workShift){
        try {
            employeeService.addWorkShift(workShift);
            return ResponseEntity.ok(workShift);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
