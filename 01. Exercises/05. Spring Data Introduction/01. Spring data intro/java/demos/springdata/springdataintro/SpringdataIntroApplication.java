package demos.springdata.springdataintro;

import demos.springdata.springdataintro.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringdataIntroApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringdataIntroApplication.class, args);
    }

}
