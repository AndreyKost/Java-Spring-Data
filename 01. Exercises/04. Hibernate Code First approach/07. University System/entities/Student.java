package entities.university_system;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "student")
public class Student extends Person{
    private String email;
    private double salaryPerHour;
    private Set<Course> courseSet;

    public Student() {
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "salary_per_hour")
    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @ManyToMany
    @JoinTable(name = "students_courses",joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"))
    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }
}
