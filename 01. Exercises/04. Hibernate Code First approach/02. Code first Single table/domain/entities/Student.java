package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name="students")
@DiscriminatorValue(value="student")
public class Student extends Person{
    private long id;
    private String firstName;
    private String lastName;
    private int grade;


    public Student() {
    }

    public Student(String firstName, String lastName, int grade) {
        super(firstName,lastName);
        setGrade(grade);
    }



    @Column
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
