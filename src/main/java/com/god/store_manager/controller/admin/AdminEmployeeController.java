package com.god.store_manager.controller.admin;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.employee.Employee;
import com.god.store_manager.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminEmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity getListEmployeeWithPagination(
            @RequestParam(required = false) PaginationRequest paginationRequest){
        return ResponseEntity.ok(employeeService.getListEmployeeByPage(paginationRequest));
    }
    @GetMapping("/employees/info")
    public ResponseEntity getEmployeeInfo(@RequestParam long employeeId){
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
        if(employee.isPresent()){
            return ResponseEntity.ok(employee.get());
        }
        else {
            return ResponseEntity.status(404).body("employee not exist, please check employeeId and try again");
        }
    }
    @PostMapping("/employees/update")
    public ResponseEntity updateEmployeeInfo(@RequestBody Employee employee){
        try {
            employeeService.updateEmployee(employee);
            return ResponseEntity.ok(employee);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping("/employees/add")
    public ResponseEntity addEmployeeInfo(@RequestBody Employee employee){
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.ok(employee);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @GetMapping("/work-shift/by-employee")
    public ResponseEntity getListWorkShiftByEmployeeWithPagination(
            @RequestParam(required = false) PaginationRequest paginationRequest,
            @RequestParam long employeeId){
        try{
            return ResponseEntity.ok(employeeService.getListWorkShiftByEmployeeIdWithPaginate(paginationRequest,employeeId));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Cannot get work shift, please try again later");
        }
    }
    @GetMapping("/work-shift/by-employee-and-period")
    public ResponseEntity getListWorkShiftByEmployeeAndPeriodWithPagination(
            @RequestParam(required = false) PaginationRequest paginationRequest,
            @RequestParam long employeeId,
            @RequestBody Date dateStart,
            @RequestBody Date dateEnd){
        try{
            return ResponseEntity.ok(employeeService
                    .getListWorkShiftByEmployeeAndPerxiodWithPagination(
                            dateStart,
                            dateEnd,
                            employeeId,
                            paginationRequest));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Cannot get work shift, please try again later");
        }
    }
}
