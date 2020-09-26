package mapping.demo.data.services.service;

import mapping.demo.data.services.dtos.CityDto;
import mapping.demo.data.services.dtos.CitySeedDto;


public interface CityService {

    void save(CitySeedDto citySeedDto);

    CityDto findByName(String name);
}
