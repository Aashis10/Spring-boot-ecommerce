package com.myapp.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemRequestDto {


    private Long cartId;
    private Long productId;
    private Integer quantity;
    // getters and setters
}