package lt.bit.jpa;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import lt.bit.data.Person;

/**
 *
 * @author Gabrielius
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, 2);
        System.out.println("------------------");
        System.out.println(p);
        System.out.println("------------------");
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Person pNew = new Person();
        pNew.setFirstName("test1111");
        pNew.setFirstName("test last 1111");
        pNew.setBirthDate(new Date());
        pNew.setSalary(BigDecimal.TEN);
        em.persist(pNew);
        em.flush();
        tx.commit();
        em.close();
        emf.close();
        System.out.println("pNew");
    }

}
