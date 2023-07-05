package com.god.store_manager.repository.customer;

import com.god.store_manager.model.customer.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByUserId(long userId);

    List<Order> findAllByDistrictId(long districtId);
    List<Order> findAllByProvinceId(long provinceId);
    List<Order> findAllByWardId(long wardId);
}
