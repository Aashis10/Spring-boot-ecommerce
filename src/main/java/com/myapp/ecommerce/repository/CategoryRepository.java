package com.myapp.ecommerce.repository;

import com.myapp.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
     List<Category> findAll();

     Category save(Category c);

     Optional<Category> findById(Long id);




}
