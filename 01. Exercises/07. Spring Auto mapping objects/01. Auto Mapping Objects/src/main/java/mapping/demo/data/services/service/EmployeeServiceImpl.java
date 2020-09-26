package mapping.demo.data.services.service;

import mapping.demo.data.entities.City;
import mapping.demo.data.entities.Employee;
import mapping.demo.data.repositories.CityRepostiory;
import mapping.demo.data.repositories.EmployeeRepository;
import mapping.demo.data.services.dtos.CityDto;
import mapping.demo.data.services.dtos.EmployeeSeedDto;
import mapping.demo.data.services.dtos.EmployeeViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final CityRepostiory cityRepostiory;
    private final CityService cityService;

    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository, CityRepostiory cityRepostiory, CityService cityService) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.cityRepostiory = cityRepostiory;
        this.cityService = cityService;
    }

    @Override
    public void save(EmployeeSeedDto employeeSeedDto) {
        Employee employee=this.modelMapper.map(employeeSeedDto,Employee.class);
        CityDto cityDto=this.cityService.findByName(employeeSeedDto.getAddressCity());
        employee.setCity(this.modelMapper.map(cityDto, City.class));

        this.employeeRepository.save(employee);

    }

    @Override
    public EmployeeViewDto findByFirstAndLastName(String firstName, String lastName) {
        Employee employee = employeeRepository.findByFirstNameAndLastName(firstName, lastName);

        EmployeeViewDto employeeViewDto=modelMapper.map(employee,EmployeeViewDto.class);
        return employeeViewDto;
    }
}
