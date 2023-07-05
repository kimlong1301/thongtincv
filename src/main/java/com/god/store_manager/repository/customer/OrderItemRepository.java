package com.god.store_manager.repository.customer;

import com.god.store_manager.model.auth.User;
import com.god.store_manager.model.customer.OrderItem;
import com.god.store_manager.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Product findByProductId(long productId);
}
