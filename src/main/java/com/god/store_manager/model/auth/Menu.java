package com.god.store_manager.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String menuName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu parentMenu;
    @OneToMany(mappedBy = "parentMenu",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Menu> children;
    @OneToOne(mappedBy = "menu",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private RoleMenu roleMenu;
}
