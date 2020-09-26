package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@DiscriminatorValue(value = "teacher")
public class Teacher extends Person {
    private long id;
    private String firstName;
    private String lastName;
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
}
