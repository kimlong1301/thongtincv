package com.god.store_manager.service;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.auth.Menu;
import com.god.store_manager.model.auth.User;
import com.god.store_manager.repository.auth.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepository;
    private MenuPermissionRepository menuPermissionRepository;
    private RoleRepository roleRepository;
    private RoleMenuRepository roleMenuRepository;
    private UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername())!=null){
            return false;
        }
        user.setAdmin(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    public boolean updateCurrentUser(User updatedUser) {
        User currentUser = getCurrentUser();
        if(currentUser.getUsername().equals(updatedUser.getUsername())) {
            currentUser.importUserData(updatedUser);
            currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            userRepository.save(currentUser);
            return true;
        }
        else{
            return false;
        }
    }
    public Page<User> getAccountsWithPagination(
           @Nullable PaginationRequest paginationRequest
    ) {
        return userRepository.findAll(
                PaginationRequest.getPageRequest(paginationRequest,"")
        );
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }
    public boolean addNewMenu(Menu menu){
        try {
            menuRepository.save(menu);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean updateMenu(Menu menu){
        try{
            if(!menuRepository.existsById(menu.getId())) return false;
            menuRepository.save(menu);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean deleteMenuById(long menuId){
        try{
            menuRepository.deleteById(menuId);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    public User getUserById(long userId) {
        return userRepository.getReferenceById(userId);
    }

    public boolean addMenu(Menu menu) {
        try {
            menuRepository.save(menu);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean checkMenuExitById(long id) {
        return menuRepository.existsById(id);
    }
}


