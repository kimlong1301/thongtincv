package com.god.store_manager.service;

import com.god.store_manager.DTO.CartItemRequest;
import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.auth.User;
import com.god.store_manager.model.customer.CartItem;
import com.god.store_manager.model.customer.Customer;
import com.god.store_manager.model.customer.Order;
import com.god.store_manager.repository.auth.UserRepository;
import com.god.store_manager.repository.customer.CartItemRepository;
import com.god.store_manager.repository.customer.CustomerRepository;
import com.god.store_manager.repository.employee.EmployeeRepository;
import com.god.store_manager.util.Exception.EntityExistingException;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final EmployeeRepository employeeRepository;
    public Page<Customer>getCustomersWithPagination(@Nullable PaginationRequest paginationRequest){
        return customerRepository.findAll(PaginationRequest.getPageRequest(paginationRequest,""));
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public List<Customer> findCustomerByName(String customerName){
        try {
            List<User>users = userRepository.findAllByFullName(customerName);
            List<Long> listUserId = users.stream().map(user->user.getId()).toList();
            return customerRepository.findByUserIdIn(listUserId);
        }
        catch (Exception e){
            return null;
        }
    }
    public boolean checkCustomerExitById(long customerId){
        return customerRepository.existsById(customerId);
    }
    public Optional<Customer> findCustomerById(long customerId){
        return customerRepository.findById(customerId);
    }
    public boolean addCustomer(Customer customer){
        long userId = customer.getUserId();
        if(userRepository.existsById(userId)){
            if(employeeRepository.existsByUserId(userId))
                throw new EntityExistingException("UserId "+userId+" has been used for an employee");

            customerRepository.save(customer);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean updateCustomer(Customer customer){
        try{
            Optional<Customer> target = customerRepository.findById(customer.getId());
            if(!target.isPresent()) return false;
            customerRepository.save(customer);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean deleteCustomerById(long customerId){
        Optional<Customer> target = customerRepository.findById(customerId);
        if(!target.isPresent()) return false;
        Optional<User> customerAccount = userRepository.findById(target.get().getUserId());
        if(customerAccount.isPresent()){
            userRepository.deleteById(customerAccount.get().getId());
        }
        customerRepository.deleteById(customerId);
        return true;
    }
    public Page<CartItem> getListCartItemByUserId(@Nullable PaginationRequest paginationRequest, long userId){
        CartItem cartItemExample = new CartItem();

        cartItemExample.setUserId(userId);

        return cartItemRepository.findAll(Example.of(cartItemExample),
                PaginationRequest.getPageRequest(paginationRequest,""));
    }
    public CartItem getCartItemById(long cartItemId){
        try{
            return cartItemRepository.getReferenceById(cartItemId);
        }
        catch (Exception e){
            return  null;
        }
    }
    public boolean addOrUpdateListCartItem(List<CartItemRequest> cartItemRequests) {
        try {
            List<CartItem> listNewCartItem = new ArrayList<>();
            for (CartItemRequest cartItemRequest : cartItemRequests) {
                CartItem crrCartItem = new CartItem();
                Optional<CartItem> cartExist = cartItemRepository
                        .findByUserIdAndProductId(cartItemRequest.getUserId(), cartItemRequest.getProductId());

                if (cartExist.isPresent()) {
                    crrCartItem = cartExist.get();
                    crrCartItem.setQuantity(cartItemRequest.getQuantity());
                }
                else{
                    crrCartItem.importFromCartItemRequest(cartItemRequest);
                }
                listNewCartItem.add(crrCartItem);
            }
            cartItemRepository.saveAll(listNewCartItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteCartItemByListId(List<Long> cartItemIds){
        try {
            cartItemRepository.deleteAllById(cartItemIds);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Optional<Customer> findCustomerByUserId(long userId) {
        return customerRepository.findByUserId(userId);
    }
}
