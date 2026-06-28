package com.myapp.ecommerce.service;

import com.myapp.ecommerce.dto.CartItemRequestDto;
import com.myapp.ecommerce.entities.Cart;
import com.myapp.ecommerce.entities.CartItem;
import com.myapp.ecommerce.entities.Product;
import com.myapp.ecommerce.entities.User;
import com.myapp.ecommerce.repository.CartItemRepository;
import com.myapp.ecommerce.repository.CartRepository;
import com.myapp.ecommerce.repository.ProductRepository;
import com.myapp.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Cart
    public String addCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart();
        cart.setUser(user);

        cartRepository.save(cart);

        return "Cart Created Successfully";
    }

    // Delete Cart
    public String deleteCart(Long cartId) {

        cartRepository.deleteById(cartId);

        return "Cart Deleted Successfully";
    }

    // Add Item to Cart
//    public String addItem(CartItemRequestDto request) {
//
//        Cart cart = cartRepository.findById(request.getCartId())
//                .orElseThrow(() -> new RuntimeException("Cart not found"));
//
//        Product product = productRepository.findById(request.getProductId())
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        CartItem item = new CartItem();
//        item.setCart(cart);
//        item.setProduct(product);
//        item.setQuantity(request.getQuantity());
//
//        cartItemRepository.save(item);
//
//        return "Item Added Successfully";
//    }

    public String addItem(CartItemRequestDto request) {

        System.out.println("Cart Id = " + request.getCartId());
        System.out.println("Product Id = " + request.getProductId());

        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        System.out.println("Cart Found");

        Product product = null;
        try {
            product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            System.out.println("Product Found");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Product Found");

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        System.out.println("Saving...");

        cartItemRepository.save(item);

        System.out.println("Saved");

        return "Item Added Successfully";
    }

    // Get All Cart Items
    public List<CartItem> getAllCartItems() {

        return cartItemRepository.findAll();
    }

    // Delete Cart Item
    public String deleteItem(Long id) {

        cartItemRepository.deleteById(id);

        return "Cart Item Deleted Successfully";
    }
}