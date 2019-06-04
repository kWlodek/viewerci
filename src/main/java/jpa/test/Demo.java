package jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        EntityManager em = Persistence
                            .createEntityManagerFactory("viewer")
                            .createEntityManager();
        Person p = new Person();
        p.setName("Eli");
        p.setBirth(new Date());

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }
}
