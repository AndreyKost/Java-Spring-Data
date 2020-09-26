package demos.springdata.springdataintro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="students")
@Data
@NoArgsConstructor

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "registration_date")
    private String registrationDate;


    public Student(String name) {
        this.name=name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                '}';
    }
}
