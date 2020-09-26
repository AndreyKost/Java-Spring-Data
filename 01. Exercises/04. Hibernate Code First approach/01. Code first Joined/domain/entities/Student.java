package app.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="students")
public class Student extends Person {

    private int grade;

    public Teacher teacher;

    private Project project;

    private Set<Project> projects;

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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

   /*  @ManyToOne
     @JoinColumn(name = "project_id",referencedColumnName = "id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }*/

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "students_projects",joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "project_id",referencedColumnName = "id"))
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
