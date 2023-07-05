package com.god.store_manager.controller.admin;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.util.JwtUtil;
import com.god.store_manager.model.auth.User;
import com.god.store_manager.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminUserController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/accounts")
    public ResponseEntity getAccountsWithPagination(@RequestBody(required = false) PaginationRequest paginationRequest) {
        try {
            return ResponseEntity.ok(authService.getAccountsWithPagination(paginationRequest));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Cannot get list user, please try again later");
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity addAccount(@RequestBody List<User> users) {
        try {
            List<User> listUserCannotCreated = new ArrayList<>();

            for (User user:
                    users ) {
                if(!authService.registerUser(user)){
                    listUserCannotCreated.add(user);
                }
            }
            if(listUserCannotCreated.stream().count()>0){
                return ResponseEntity.status(402).body(listUserCannotCreated);
            }
            else{
                return ResponseEntity.ok().body(users);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("There are some errors occurred, please try again later");
        }
    }

}

