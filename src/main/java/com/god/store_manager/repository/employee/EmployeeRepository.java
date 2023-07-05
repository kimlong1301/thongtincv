package com.god.store_manager.repository.employee;

import com.god.store_manager.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByUserId(long userId);

    boolean existsByUserId(long userId);
}
