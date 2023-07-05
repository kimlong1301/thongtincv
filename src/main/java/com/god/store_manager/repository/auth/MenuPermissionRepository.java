package com.god.store_manager.repository.auth;

import com.god.store_manager.model.auth.MenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuPermissionRepository extends JpaRepository<MenuPermission, Long> {
}
