package entities.university_system;

import entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="courses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Course extends BaseEntity {
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double credits;
    private Set<Student> studentSet;
    private Teacher teacher;

    public Course() {
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="start_date")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name="end_date")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Column(name="credits")
    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    @ManyToMany(mappedBy = "courseSet")
    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
