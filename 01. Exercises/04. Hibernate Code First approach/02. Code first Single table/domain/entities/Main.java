package app.domain.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("codeFirst");

        EntityManager entityManager=entityManagerFactory.createEntityManager();

        Student student=new Student("Gosho","Georgiev",8);
        Teacher teacher=new Teacher("Dimitur","Dimitrov","Java m");

        entityManager.getTransaction().begin();
        //Person person=entityManager.find(Student.class,(long)1);
        entityManager.persist(student);
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
