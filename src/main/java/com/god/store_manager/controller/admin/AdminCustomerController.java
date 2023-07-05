package com.god.store_manager.controller.admin;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.customer.Customer;
import com.god.store_manager.service.AuthService;
import com.god.store_manager.service.CustomerService;
import com.god.store_manager.service.LocationService;
import com.god.store_manager.util.Exception.EntityExistingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminCustomerController {

    private final AuthService authService;
    private final CustomerService customerService;
    private final LocationService locationService;

    @GetMapping("/customers")
    public ResponseEntity getCustomersWithPagination(@RequestBody(required = false) PaginationRequest paginationRequest) {
        try {
            return ResponseEntity.ok(customerService.getCustomersWithPagination(paginationRequest));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Cannot get list user, please try again later");
        }
    }
    @GetMapping("/customers/delete")
    public ResponseEntity deleteCustomerById(@RequestParam int id){
        try {
            if (customerService.deleteCustomerById(id)){
                return ResponseEntity.ok("Delete success");
            }
            else{
                return ResponseEntity.status(404).body("Customer id not found, please try again later");
            }
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Cannot delete customer, please check constraint and try again later");
        }
    }
    @PostMapping("/customers/add")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        long userId = customer.getUserId();
        try{
            authService.getUserById(userId);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(404).body("user not found with provided userId");
        }
        Optional<Customer> customerWithUserIdExit = customerService.findCustomerByUserId(userId);
        if(customerWithUserIdExit.isPresent()){
            return ResponseEntity.status(409).body("The userId has been used by other customer");
        }
        try {
            if (customerService.addCustomer(customer)) {
                return ResponseEntity.status(201).body("Created the customer");
            } else {
                return ResponseEntity.status(500).body("Something went wrong, please try again later");
            }
        }
        catch (EntityExistingException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping("/customers/update")
    public ResponseEntity updateCustomer(@RequestBody Customer customer){
        if(customerService.checkCustomerExitById(customer.getId())){
            if(!locationService.validateLocation(
                    customer.getWardId(),
                    customer.getDistrictId(),
                    customer.getProvinceId())){
                ResponseEntity.status(404).body("The location is not correct, please check all location" +
                        " info and try again");
            }
            if(customerService.updateCustomer(customer)){
                return ResponseEntity.status(201).body("Update customer success");
            }
            else{
                return ResponseEntity.status(500).body("Something went wrong, please try again later");
            }
        }
        else {
            return ResponseEntity.status(404).body("CustomerId not found");
        }
    }
}
