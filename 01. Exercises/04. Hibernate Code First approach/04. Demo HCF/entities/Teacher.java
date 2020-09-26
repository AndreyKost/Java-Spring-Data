package entities.demo_hcf;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="teachers")
public class Teacher extends Person {
    private String speciality;
    private Set<Course> courseSet;

    public Teacher() {
    }

    @Column(name="speciality")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @OneToMany(mappedBy = "teacher",targetEntity = Course.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }
}
