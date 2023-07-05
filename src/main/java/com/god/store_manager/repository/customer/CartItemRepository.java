package com.god.store_manager.repository.customer;

import com.god.store_manager.model.customer.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUserId(long userId);
    List<CartItem> findAllByProductId(long productId);
    Optional<CartItem> findByUserIdAndProductId(long userId,long productId);
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.id IN :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    boolean existsByProductId(long productId);

    Page<CartItem> getListCartItemByUserId(PageRequest pageRequest, long userId);
}
