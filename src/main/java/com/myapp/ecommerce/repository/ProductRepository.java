package com.myapp.ecommerce.repository;

import com.myapp.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

   Product save(Product p);

   @Override
   List<Product> findAll();


   Optional<Product> findById(Long id);

   List<Product> findByName(String name);

   List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);


}

