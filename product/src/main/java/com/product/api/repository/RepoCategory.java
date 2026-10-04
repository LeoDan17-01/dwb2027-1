package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.product.api.entity.Category;
import com.product.api.dto.DtoCategoryIn;

import java.util.List;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {

    @Query(value = "Select * from category order by category_id", nativeQuery = true)
    List<Category> findAll();

    @Query(value = "Select * from category where status = 1 order by category_id", nativeQuery = true)
    List<Category> findActive();

    //@Query(value = "Insert into category (category, tag, parent_category_id, status) values (:#{#in.category}, :#{#in.tag}, :#{#in.parentCategoryId}, :#{#in.status})", nativeQuery = true)
    //create(DtoCategoryIn in);
}