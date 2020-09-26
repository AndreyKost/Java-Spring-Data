package mapping.demo;

import mapping.demo.data.entities.City;
import mapping.demo.data.services.dtos.CityDto;
import mapping.demo.data.services.dtos.CitySeedDto;
import mapping.demo.data.services.service.CityService;
import mapping.demo.data.services.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CmdRunner implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final CityService cityService;

    public CmdRunner(EmployeeService employeeService, CityService cityService) {
        this.employeeService = employeeService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {

        /*EmployeeSeedDto employeeSeedDto=new EmployeeSeedDto("Ivan",3500,"Varna");


        employeeService.save(employeeSeedDto);*/

        //EmployeeViewDto employeeViewDto=this.employeeService.findByFirstAndLastName("Ivan","Draganov");

        CitySeedDto cityDto1 =new CitySeedDto("Sofia");
        CitySeedDto cityDto2 =new CitySeedDto("Plovdiv");
        CitySeedDto cityDto3 =new CitySeedDto("Varna");

        this.cityService.save(cityDto1);
        this.cityService.save(cityDto2);
        this.cityService.save(cityDto3);

    }
}
