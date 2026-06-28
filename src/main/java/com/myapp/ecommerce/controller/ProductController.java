package com.myapp.ecommerce.controller;

import com.myapp.ecommerce.dto.ProductRequestDto;
import com.myapp.ecommerce.dto.ProductResponseDto;
import com.myapp.ecommerce.entities.Product;
import com.myapp.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        ProductResponseDto product =    productService.createproduct(productRequestDto);
        return  new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
       Product p = productService.getById(id);
       return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @PatchMapping("/{id}")
    public Product patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.patchProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }

    @GetMapping("/search")
    public List<Product> searchByName(
            @RequestParam String name) {

        return productService.searchByName(name);
    }

    @GetMapping("/price-range")
    public List<Product> searchByPriceRange(
            @RequestParam   BigDecimal minPrice,
            @RequestParam (required = false) BigDecimal maxPrice) {
  return productService.searchByPriceRange(
                minPrice,
                maxPrice
        );
    }







}
