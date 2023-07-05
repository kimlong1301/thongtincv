package com.god.store_manager.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long RoleID;
    @Column(nullable = false,unique = true)
    private String RoleName;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserRole> userRole;
    @OneToOne(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private RoleMenu roleMenu;
}
