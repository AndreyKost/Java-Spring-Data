package mapping.demo.data.repositories;

import mapping.demo.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByFirstNameAndLastName(String firstName,String lastName);
}
