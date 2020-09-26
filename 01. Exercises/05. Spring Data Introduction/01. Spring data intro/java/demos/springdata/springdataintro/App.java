package demos.springdata.springdataintro;

import demos.springdata.springdataintro.entities.Student;
import demos.springdata.springdataintro.repositories.StudentsRepository;
import demos.springdata.springdataintro.services.base.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class App implements CommandLineRunner {

    @Autowired
    private StudentsService studentsService;

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("it works !");
        studentsService.getAll().forEach(System.out::println);

        //studentsService.findAll().forEach(System.out::println);

    }
}
