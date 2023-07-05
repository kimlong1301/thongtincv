package com.god.store_manager.controller.admin;

import com.god.store_manager.model.auth.Menu;
import com.god.store_manager.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminMenuController {

    private final AuthService authService;

    @GetMapping("/menus")
    public ResponseEntity getAllMenus() {
        try {
            return ResponseEntity.ok(authService.getAllMenu());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Cannot get list user, please try again later");
        }
    }

    @GetMapping("/menus/delete")
    public ResponseEntity deleteMenuById(@RequestParam long menuId) {
        try {
            if (authService.deleteMenuById(menuId)) {
                return ResponseEntity.ok("Delete success");
            } else {
                return ResponseEntity.status(404).body("Menu id not found, please try again later");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Cannot delete menu, please check constraint and try again later");
        }
    }

    @PostMapping("/menus/add")
    public ResponseEntity addMenu(@RequestBody Menu menu) {
        if (authService.addMenu(menu)) {
            return ResponseEntity.status(201).body("Created the menu");
        } else {
            return ResponseEntity.status(500).body("Something went wrong, please try again later");
        }
    }

    @PostMapping("/menus/update")
    public ResponseEntity updateMenu(@RequestBody Menu menu) {
        if (authService.checkMenuExitById(menu.getId())) {
            if (authService.updateMenu(menu)) {
                return ResponseEntity.status(201).body("Update menu success");
            } else {
                return ResponseEntity.status(500).body("Something went wrong, please try again later");
            }
        } else {
            return ResponseEntity.status(404).body("MenuId not found");
        }
    }
}
