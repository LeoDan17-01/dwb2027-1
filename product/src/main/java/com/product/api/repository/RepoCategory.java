package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.product.api.entity.Category;

import java.util.List;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {

    @Query(value = "Select * from category order by category_id", nativeQuery = true)
    List<Category> getCategories();
}