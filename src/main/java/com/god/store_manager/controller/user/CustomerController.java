package com.god.store_manager.controller.user;

import com.god.store_manager.DTO.CartItemRequest;
import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.auth.User;
import com.god.store_manager.service.AuthService;
import com.god.store_manager.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final AuthService authService;

    @GetMapping("/cart")
    public ResponseEntity getCartListWithPagination(@RequestParam(required = false)PaginationRequest paginationRequest){
        try{
            User currentUser = authService.getCurrentUser();
            return ResponseEntity.ok(customerService.getListCartItemByUserId(paginationRequest,currentUser.getId()));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Something went wrong, please try again later");
        }
    }
    @PostMapping("/cart/add")
    public ResponseEntity addListCartItemToCart(@RequestBody List<CartItemRequest> cartItems){
        try{
            customerService.addOrUpdateListCartItem(cartItems);
            return ResponseEntity.ok("Add to cart success");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Add to cart failed");
        }
    }
    @PostMapping("/cart/update")
    public ResponseEntity updateListCartItemToCart(@RequestBody List<CartItemRequest> cartItems){
        try{
            customerService.addOrUpdateListCartItem(cartItems);
            return ResponseEntity.ok("Update cart success");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Updatecart failed");
        }
    }
}
