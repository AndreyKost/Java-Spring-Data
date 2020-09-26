package entities.university_system;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(value="teacher")
public class Teacher extends Person{
    private double averageGrade;
    private int attendance;
    private Set<Course> courseSet;

    public Teacher() {
    }

    @Column(name="average_grade")
    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name = "attendance")
    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    @OneToMany(mappedBy = "teacher")
    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }
}
