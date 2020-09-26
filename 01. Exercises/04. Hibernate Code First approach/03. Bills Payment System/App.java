//import entities.demo_hcf.Student;
import entities.sales.Product;
import entities.sales.Sale;

import javax.persistence.*;

public class App {
    private static final String GRINGOTTS_PU="gringotts";
    private static final String SALES_PU="sales";
    private static final String UNI_SYS_PU="university_system";
    private static final String BILL_SYS_PU="bills_payment_system";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory(BILL_SYS_PU);


       /* EntityManager entityManager=entityManagerFactory.createEntityManager();
        Engine engine=new Engine(entityManager);
        engine.run();*/


    }

}
