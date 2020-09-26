package mapping.demo.data.services.service;

import mapping.demo.data.entities.Employee;
import mapping.demo.data.services.dtos.EmployeeSeedDto;
import mapping.demo.data.services.dtos.EmployeeViewDto;

public interface EmployeeService {
    void save(EmployeeSeedDto employeeSeedDto);

    EmployeeViewDto findByFirstAndLastName(String firstName, String lastName);

}
