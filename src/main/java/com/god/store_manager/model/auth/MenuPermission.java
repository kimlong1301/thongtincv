package com.god.store_manager.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Menu_Permissions")
public class MenuPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(mappedBy = "menuPermission",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private RoleMenu roleMenu;
    @Column(columnDefinition = "boolean default false")
    private boolean canView;
    @Column(columnDefinition = "boolean default false")
    private boolean canCreate;
    @Column(columnDefinition = "boolean default false")
    private boolean canUpdate;
    @Column(columnDefinition = "boolean default false")
    private boolean canDelete;
}
