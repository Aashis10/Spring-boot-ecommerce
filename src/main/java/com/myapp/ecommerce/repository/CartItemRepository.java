package com.myapp.ecommerce.repository;

import com.myapp.ecommerce.entities.Cart;
import com.myapp.ecommerce.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

}