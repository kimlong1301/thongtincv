package com.god.store_manager.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Role_Menus")
public class RoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    @JoinColumn(name = "roleId")
    private Role role;
    @OneToOne()
    @JoinColumn(name = "menuId")
    private Menu menu;
    @OneToOne()
    @JoinColumn(name = "menu_permissionId")
    private MenuPermission menuPermission;
}
