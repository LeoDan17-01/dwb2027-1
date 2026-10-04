package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {

    // Aprovechamos JPA para buscar una categoria por su status
    List<Category> findByStatus(Integer status);

    List<Category> findByStatusOrderByCategoryIdAsc(Integer status);

    @Query(value = "Select * from category order by category_id", nativeQuery = true)
    List<Category> findAll();

    @Query(value = "Select * from category where status = 1 order by category_id", nativeQuery = true)
    List<Category> findActive();

    // Consulta para ver las categorias hijo de una categoria padre
    @Query (value = "Select * from category where parent_category_id = :id order by category_id", nativeQuery = true)
    List<Category> findChilds(@Param("id") Integer id);

    // Consulta para crear una categoria
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO region(region, tag, status) VALUES (:region, :tag, 1)", nativeQuery = true)
    void create(@Param("region") String region, @Param("tag") String tag);

}