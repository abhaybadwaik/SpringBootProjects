package com.example.ReviewProject.repoSQL;

import com.example.ReviewProject.SqlEntity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySqlRepository extends JpaRepository<CustomerEntity,Long> {
}
