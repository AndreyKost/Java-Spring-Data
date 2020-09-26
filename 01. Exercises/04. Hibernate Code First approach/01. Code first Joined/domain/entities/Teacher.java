package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends Person{
    private Student student;
    private String degree;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String degree) {
        super(firstName,lastName);
        setDegree(degree);
    }


    @Column
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @OneToOne(mappedBy = "teacher")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
