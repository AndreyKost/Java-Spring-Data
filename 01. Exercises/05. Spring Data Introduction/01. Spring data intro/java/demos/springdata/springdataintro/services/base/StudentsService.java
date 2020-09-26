package demos.springdata.springdataintro.services.base;

import demos.springdata.springdataintro.entities.Student;

import java.util.List;

public interface StudentsService {
    List<Student> getAll();

    List<Student> findByNamePattern(String name);
}
