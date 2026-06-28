package com.myapp.ecommerce.dto;

import java.math.BigDecimal;

 public record ProductResponseDto(
        String name,
        BigDecimal price,
        Long categoryId,
       String categoryName
) {}