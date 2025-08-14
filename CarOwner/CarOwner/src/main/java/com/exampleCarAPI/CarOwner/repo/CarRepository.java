package com.exampleCarAPI.CarOwner.repo;

import com.exampleCarAPI.CarOwner.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface CarRepository extends JpaRepository<CarEntity,Integer> {

    Optional<CarEntity> findByBrand(String brandName);

    Optional<CarEntity> findByColor(String colorName);

    void deleteByModel(String modelName);
}
