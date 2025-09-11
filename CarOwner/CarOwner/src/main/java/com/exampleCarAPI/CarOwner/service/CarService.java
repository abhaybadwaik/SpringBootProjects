package com.exampleCarAPI.CarOwner.service;

import com.exampleCarAPI.CarOwner.entity.CarEntity;
import com.exampleCarAPI.CarOwner.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService{
    @Autowired
    CarRepository carRepository;

    public CarEntity addCar(CarEntity car){
        return carRepository.save(car);
    }
    public List<CarEntity> addAllCar(List<CarEntity> cars){
        return carRepository.saveAll(cars);
    }

    public CarEntity findById(int Id){

        return carRepository.findById(Id).orElseThrow(()-> new RuntimeException("Id Not Found"));
    }
    public CarEntity findByBrand(String brand){
        return carRepository.findByBrand(brand).orElseThrow(()-> new RuntimeException("Brand NOt Found"));
    }
    public CarEntity findByColor(String color){
        return carRepository.findByColor(color).orElseThrow(()-> new RuntimeException("COLOR NAME NOT FOUND"));
    }

    public String deleteById(int id){
        carRepository.deleteById(id);
        return "Your id was deleted successfully";
    }
    public String deleteByModel(String model){
        carRepository.deleteByModel(model);
        return "Your mmodel was deleted";
    }

    public CarEntity updateById(CarEntity car){
        CarEntity carEntity = carRepository.findById(car.getId()).orElseThrow(() -> new RuntimeException("Id not Found"));
            carEntity.setBrand(car.getBrand());
            carEntity.setColor(car.getColor());
            carEntity.setModel(car.getModel());
            carEntity.setYear(car.getYear());

            return carRepository.save(carEntity);

    }
    public CarEntity updateCarById(Integer id, CarEntity updatedCar) {
        updatedCar.setId(id);          // Set the ID to the path variable
        return carRepository.save(updatedCar);  // Save will update if ID exists, else insert new
    }


}