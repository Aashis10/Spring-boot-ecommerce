package com.myapp.ecommerce.repository;

import com.myapp.ecommerce.entities.Cart;
import com.myapp.ecommerce.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long>{


    Cart findByUserId(Long userId);

}
