package app.domain.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("codeFirst");

        EntityManager entityManager=entityManagerFactory.createEntityManager();

        Student student=new Student("Gosho","Georgiev",8);
        //Teacher teacher=new Teacher("Dimitur","Dimitrov","Java m");
        //student.setTeacher(teacher);
        Set<Project> projects=new HashSet<>();

        Project project=new Project("Math");
        Project project1=new Project("Programming");

        projects.add(project);
        projects.add(project1);


        student.setProjects(projects);
        entityManager.getTransaction().begin();
        //Person person=entityManager.find(Student.class,(long)1);
        entityManager.persist(student);
        //entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
        //System.out.println(student.getTeacher().getDegree());
        //Teacher teacher1 = student.getTeacher();
        //
        //
        // teacher1.getStudent().getGrade();

        Project project2= entityManager.find(Project.class,(long)1);


    }
}
