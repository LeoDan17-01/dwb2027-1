package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

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

    // Se verifica que el nombre no se duplique
    @Query(value = "SELECT COUNT(c) > 0 FROM category c WHERE c.category = :category", nativeQuery = true)
    boolean existsByCategory(@Param("category") String category);

    // Se verifica que el tag no se duplique
    @Query(value = "SELECT COUNT(c) > 0 FROM category c WHERE c.tag = :tag", nativeQuery = true)
    boolean existsByTag(@Param("tag") String tag);

    // El padre debe existir y estar activo
    @Query(value = "SELECT * FROM category WHERE category_id = :id AND status = 1", nativeQuery = true)
    Optional<Category> findActiveById(@Param("id") Integer id);

    // Se verifica si la categoria tiene hijos
    @Query(value = "SELECT COUNT(c) > 0 FROM category c WHERE c.parent_category_id = :id", nativeQuery = true)
    boolean hasChildren(@Param("id") Integer id);

    // Consulta para crear una categoria
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO category(category, tag, parent_category, status) VALUES (:category, :tag, 1)", nativeQuery = true)
    void create(@Param("category") String category, @Param("tag") String tag, @Param("parent_category_id") Integer parent_category_id);

    // Consulta para actualizar una categoria
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value ="UPDATE category SET category = :category, tag = :tag, parent_category_id = :parent_category_id WHERE category_id = :category_id", nativeQuery = true)
    void update(@Param("category_id") Integer category_id, @Param("category") String category, @Param("tag") String tag, @Param("parent_category_id") Integer parent_category_id) ;

    // Consulta para cambiar el estatus de una categoria
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value ="UPDATE category SET status = :status WHERE category_id = :category_id", nativeQuery = true)
    void updateStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);

}