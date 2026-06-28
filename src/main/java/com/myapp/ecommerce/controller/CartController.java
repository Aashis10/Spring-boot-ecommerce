package com.myapp.ecommerce.controller;

import com.myapp.ecommerce.dto.CartItemRequestDto;
import com.myapp.ecommerce.entities.CartItem;
import com.myapp.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Create Cart
    @PostMapping("/create/{userId}")
    public String addCart(@PathVariable Long userId) {
        return cartService.addCart(userId);
    }

    // Delete Cart
    @DeleteMapping("/{cartId}")
    public String deleteCart(@PathVariable Long cartId) {
        return cartService.deleteCart(cartId);
    }

    // Add Item to Cart
    @PostMapping("/item")
    public String addItem(@RequestBody CartItemRequestDto request) {
        return cartService.addItem(request);
    }

    // Get All Cart Items
    @GetMapping("/items")
    public List<CartItem> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    // Delete Cart Item
    @DeleteMapping("/item/{id}")
    public String deleteItem(@PathVariable Long id) {
        return cartService.deleteItem(id);
    }
}








