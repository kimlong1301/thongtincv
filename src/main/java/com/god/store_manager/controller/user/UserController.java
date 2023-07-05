package com.god.store_manager.controller.user;

import com.god.store_manager.DTO.AuthenticationRequest;
import com.god.store_manager.DTO.JwtResponse;
import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.customer.Customer;
import com.god.store_manager.service.CustomerService;
import com.god.store_manager.service.LocationService;
import com.god.store_manager.util.JwtUtil;
import com.god.store_manager.model.auth.User;
import com.god.store_manager.model.customer.CartItem;
import com.god.store_manager.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final AuthService authService;
    private final CustomerService customerService;
    private final LocationService locationService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        try {
            if (!authService.registerUser(user)) {
                return ResponseEntity.status(403).body("Username exits, please chose different username");
            }
            Customer customer = new Customer();
            customer.setUserId(user.getId());
            customerService.addCustomer(customer);
            final String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Some thing wrong, please try again later");
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody(required = false) AuthenticationRequest authenticationRequest) {
        try {
            User user = authService.loadUserByUsername(authenticationRequest.getUsername());
            boolean isCorrectPassword = passwordEncoder.matches(authenticationRequest.getPassword(),
                    user.getPassword());

            if (isCorrectPassword) {
                final String token = jwtUtil.generateToken(user);
                return ResponseEntity.ok(new JwtResponse(token));
            } else {
                return ResponseEntity.status(403).body("Wrong password");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity getCurrentUserInfo() {
        User user = authService.getCurrentUser();
        Optional<Customer> currentCustomer = customerService.findCustomerByUserId(user.getId());
        Hashtable<String,Object> userInfo =  new Hashtable<String,Object>();

        userInfo.put("user",user);
        userInfo.put("customerInfo",userInfo);
        return ResponseEntity.ok(userInfo);
    }

    //NOTE: the password of all the users is raw, we have to decode it in userService
    @PutMapping("/info")
    public ResponseEntity updateCurrentUserInfo(@RequestBody User user,@RequestBody(required = false) Customer customer) {
        User currentUser = authService.getCurrentUser();
        if(customer!=null){
            Optional<Customer> currentCustomer = customerService.findCustomerByUserId(currentUser.getId());
            if(currentCustomer.isPresent()){
                customer.setId(currentCustomer.get().getId());
                if(!locationService.validateLocation(
                        customer.getWardId(),
                        customer.getDistrictId(),
                        customer.getProvinceId())){
                    return ResponseEntity.status(404).body("The location is not correct, please check all location" +
                            " info and try again");
                }
                else{
                    customerService.updateCustomer(customer);
                }
            }
            else{
                customerService.addCustomer(customer);
            }
        }
        if (authService.updateCurrentUser(currentUser)) {
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.status(403).body("You cannot modify info of another user");
        }
    }

    @GetMapping("/cart")
    public ResponseEntity getListCartItemOfCurrentUser(@RequestBody(required = false) PaginationRequest paginationRequest) {
        try {
            User currentUser = authService.getCurrentUser();
            Page<CartItem> cartItems = customerService.getListCartItemByUserId(paginationRequest, currentUser.getId());
            return ResponseEntity.ok(cartItems);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Something went wrong please try again later");
        }
    }
}
