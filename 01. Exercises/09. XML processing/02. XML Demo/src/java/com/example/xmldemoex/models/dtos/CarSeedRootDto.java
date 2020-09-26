package com.example.xmldemoex.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedRootDto {

    @XmlElement(name="car")
    private List<CarSeedDto> cars;

    public CarSeedRootDto() {
    }

    public List<CarSeedDto> getCars() {
        return cars;
    }

    public void setCars(List<CarSeedDto> cars) {
        this.cars = cars;
    }
}
