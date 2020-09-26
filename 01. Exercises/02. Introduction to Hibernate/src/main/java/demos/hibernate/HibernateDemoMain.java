package demos.hibernate;

import demos.hibernate.model.Student;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateDemoMain {
    public static void main(String[] args) {
        Configuration cfg=new Configuration();
        cfg.configure();
        SessionFactory sessionFactory=cfg.buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Student student=new Student("Ivan Petrov");
        session.save(student);
        session.getTransaction().commit();



        session.beginTransaction();
        //tranzkaciqta e read mode , da ne gubi vreme da flushva
        session.setHibernateFlushMode(FlushMode.MANUAL);
        Student student1 = session.get(Student.class, 1);
        session.getTransaction().commit();
        System.out.printf("Student: %s%n",student1);


        session.beginTransaction();
        List<Student> students=session.createQuery("FROM Student",Student.class)
                .setFirstResult(0)
                .setMaxResults(3)
                .list();
        session.getTransaction().commit();
        session.close();

        for (Student s : students) {
            System.out.println(s);
        }


        /*session.beginTransaction();
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery criteria=builder.createQuery();
        Root<Student> r=criteria.from(Student.class);
        criteria.select(r).where(builder.like(r.get("name"),"I%"));
        List<Student> studentList=session.createQuery(criteria).getResultList();
        for (Student st : studentList) {
            System.out.println(st);
        }

        session.getTransaction().commit();
        session.close();*/

    }
}
