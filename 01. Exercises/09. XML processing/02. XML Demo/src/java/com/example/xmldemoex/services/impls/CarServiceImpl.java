package com.example.xmldemoex.services.impls;

import com.example.xmldemoex.models.dtos.CarSeedDto;
import com.example.xmldemoex.models.entities.Car;
import com.example.xmldemoex.models.entities.Part;
import com.example.xmldemoex.repositories.CarRepository;
import com.example.xmldemoex.services.CarService;
import com.example.xmldemoex.services.PartService;
import com.example.xmldemoex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PartService partService;
    private final Random random;




    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, PartService partService, Random random) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.partService = partService;

        this.random = random;
    }

    @Override
    public void seedCars(List<CarSeedDto> carSeedDtos) {
        carSeedDtos.forEach(carSeedDto -> {
            if(this.validationUtil.isValid(carSeedDto)){
                if(this.carRepository.findByMakeAndModelAndTravelledDistance(carSeedDto.getMake(),
                        carSeedDto.getModel(),carSeedDto.getTravelledDistance())==null){
                    Car car=this.modelMapper.map(carSeedDto,Car.class);
                    Set<Part> randomParts = this.partService.getRandomParts();
                    car.setParts(randomParts);

                    System.out.println();
                    this.carRepository.saveAndFlush(car);

                } else {
                    System.out.println("Already in DB!");
                }

            } else {
                this.validationUtil.getViolations(carSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });

    }

    @Override
    public Car getRandomCar() {
        long randomId=this.random.nextInt((int) this.carRepository.count())+1;
        return this.carRepository.getOne(randomId);

        //return this.carRepository.getOne((long) (this.random.nextInt((int) this.carRepository.count())+1));
    }
}
