package demos.hibernate;

import demos.hibernate.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPAdemoMain {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("school");
        EntityManager em=emf.createEntityManager();
        User user=new User("John Smith");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.persist(user);
        User usr = em.find(User.class,1);
        System.out.printf("User: %s%n",usr);

        em.getTransaction().begin();
        List<User> userList=em.createQuery("SELECT a FROM User a",User.class).getResultList();
        em.getTransaction().commit();

        for (User u : userList) {
            System.out.println(u);
        }


        /*em.getTransaction().begin();
        CriteriaBuilder builder=em.getCriteriaBuilder();
        CriteriaQuery criteria=builder.createQuery();
        Root<User> r=criteria.from(User.class);
        criteria.select(r).where(builder.like(r.get("name"),"J%"));
        List<User> userList=em.createQuery(criteria).getResultList();
        for (User us : userList) {
            System.out.println(us);
        }

        em.getTransaction().commit();*/


    }
}
