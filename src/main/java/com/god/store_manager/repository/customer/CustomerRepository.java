package com.god.store_manager.repository.customer;

import com.god.store_manager.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByDistrictId(long districtId);
    List<Customer> findAllByProvinceId(long provinceId);
    List<Customer> findAllByWardId(long wardId);
    Optional<Customer> findByUserId(long userId);

    List<Customer> findByUserIdIn(List<Long> listUserId);

    boolean existsByUserId(long userId);
}
