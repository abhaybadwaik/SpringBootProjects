package com.example.ReviewProject.repoOracle;

import com.example.ReviewProject.OracleEntity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface OracleRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM ProductEntity p WHERE p.productName = :productName AND p.category = :category")
    boolean existsByProductNameAndCategory(@Param("productName") String productName,
                                           @Param("category") String category);
}
