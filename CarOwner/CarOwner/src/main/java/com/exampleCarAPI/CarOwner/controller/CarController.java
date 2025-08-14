package com.exampleCarAPI.CarOwner.controller;

import com.exampleCarAPI.CarOwner.entity.CarEntity;
import com.exampleCarAPI.CarOwner.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;
    @PostMapping("/addCar")
    public CarEntity carName(@RequestBody CarEntity car){
        return carService.addCar(car);
    }

    @PostMapping("/addAllCars")
    public List<CarEntity> addAllCars(@RequestBody List<CarEntity> cars){
        return carService.addAllCar(cars);
    }

    @GetMapping("/byId/{Id}")
    public CarEntity findById(@PathVariable int Id){
        return carService.findById(Id);
    }

    @DeleteMapping("/deleteById/{Id}")
    public String deleteById(@PathVariable int Id){
        return carService.deleteById(Id);
    }

    @PutMapping("/updateById")
    public CarEntity updateById(@RequestBody CarEntity car){
        return  carService.updateById(car);
    }

    @PutMapping("/update/{id}")
    public CarEntity updateCar(@PathVariable Integer id, @RequestBody CarEntity car){
        return carService.updateCarById(id, car);
    }

    @GetMapping("/brand/{brand}")
    public CarEntity findByBrand(@PathVariable String brand){
        return carService.findByBrand(brand);
    }
    @GetMapping("/color/{color}")
    public CarEntity findByColor(@PathVariable String color){
        return carService.findByColor(color);
    }


}