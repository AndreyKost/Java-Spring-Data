package demos.springdata.springdataintro.web;

import demos.springdata.springdataintro.entities.Student;
import demos.springdata.springdataintro.services.base.StudentsService;
import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsController {
    private StudentsService studentsService;

    public StudentsController(StudentsService studentsService)
    {
        this.studentsService = studentsService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() throws ChangeSetPersister.NotFoundException {
        List<Student> students= studentsService.getAll();

        if(students.isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }
        return students;
    }

}
