package com.god.store_manager.model.customer;

import com.god.store_manager.DTO.CartItemRequest;
import com.god.store_manager.model.auth.User;
import com.god.store_manager.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Qualifier()
@Table(name = "Cart_Items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,name = "userId")
    private long userId;
    @Column(nullable = false)
    private long productId;
    @Column(nullable = false)
    private long quantity;
    public void importFromCartItemRequest(CartItemRequest cartItem){
        userId = cartItem.getUserId();
        productId = cartItem.getProductId();
        quantity = cartItem.getQuantity();
    }
}
