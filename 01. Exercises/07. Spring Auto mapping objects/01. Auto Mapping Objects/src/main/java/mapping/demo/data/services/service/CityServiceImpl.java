package mapping.demo.data.services.service;

import mapping.demo.data.entities.City;
import mapping.demo.data.repositories.CityRepostiory;
import mapping.demo.data.services.dtos.CityDto;
import mapping.demo.data.services.dtos.CitySeedDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepostiory cityRepostiory;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepostiory cityRepostiory, ModelMapper modelMapper) {
        this.cityRepostiory = cityRepostiory;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CitySeedDto citySeedDto) {
        this.cityRepostiory.save(this.modelMapper.map(citySeedDto, City.class));
    }

    @Override
    public CityDto findByName(String name) {
        return this.modelMapper.map(this.cityRepostiory.findByName(name),CityDto.class);
    }
}
