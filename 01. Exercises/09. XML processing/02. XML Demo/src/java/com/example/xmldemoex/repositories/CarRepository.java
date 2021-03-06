package com.example.xmldemoex.repositories;

import com.example.xmldemoex.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Car findByMakeAndModelAndTravelledDistance(String make,String model,Long distance);

}
