package demos.springdata.springdataintro.repositories;

import demos.springdata.springdataintro.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository  extends JpaRepository<Student, Integer> {
}
