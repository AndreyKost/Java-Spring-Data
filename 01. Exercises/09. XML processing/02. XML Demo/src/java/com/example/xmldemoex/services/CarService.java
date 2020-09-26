package com.example.xmldemoex.services;

import com.example.xmldemoex.models.dtos.CarSeedDto;
import com.example.xmldemoex.models.entities.Car;

import java.util.List;

public interface CarService {
    void seedCars(List<CarSeedDto> carSeedDtos);

    //takiva metodi e po pravilno da go pravim s Dto
    Car getRandomCar();
}
