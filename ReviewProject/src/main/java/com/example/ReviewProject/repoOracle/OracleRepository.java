package com.example.ReviewProject.repoOracle;

import com.example.ReviewProject.OracleEntity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OracleRepository extends JpaRepository<ProductEntity,Integer> {
}
