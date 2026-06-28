package com.myapp.ecommerce.service;

import com.myapp.ecommerce.dto.ProductRequestDto;
import com.myapp.ecommerce.dto.ProductResponseDto;
import com.myapp.ecommerce.entities.Category;
import com.myapp.ecommerce.entities.Product;
import com.myapp.ecommerce.repository.CategoryRepository;
import com.myapp.ecommerce.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository ;

    @Autowired
    CategoryRepository categoryRepository;

    public ProductResponseDto createproduct(ProductRequestDto productRequestDto){

        Category category = categoryRepository.findById(productRequestDto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category with given id doesn't exists"));

        Product product = new Product();
        product.setName(productRequestDto.name());
        product.setPrice(productRequestDto.price());
        product.setCategory(category);

        Product p = productRepository.save(product);
        ProductResponseDto dto = new ProductResponseDto(p.getName(),p.getPrice(),p.getCategory().getId(),p.getCategory().getName());


        return dto;

    }



    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ProductResponseDto(
                        product.getName(),
                        product.getPrice(),
                        product.getCategory() != null ? product.getCategory().getId() : null,
                        product.getCategory() != null ? product.getCategory().getName() : null
                ))
                .toList();
    }


    public Product getById(Long id){
       Product p = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product  not found"));
       return p;
    }

    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        return productRepository.save(existingProduct);
    }


    public Product patchProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id: " + id));

        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }

        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }

        return productRepository.save(existingProduct);
    }


    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with id: " + id));

        productRepository.delete(product);
    }


    public List<Product> searchByName(String name) {

        return productRepository.findByName(name);
    }

    public List<Product> searchByPriceRange(BigDecimal minPrice,
                                            BigDecimal maxPrice) {

        return productRepository.findByPriceBetween(minPrice,
                maxPrice);
    }

}
