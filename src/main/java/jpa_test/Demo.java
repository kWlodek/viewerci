package jpa_test;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by Nico on 23.02.2018.
 */
public class Demo
{
    public static void main(String[] args)
    {
/*        EntityManager em = Persistence.createEntityManagerFactory("viewer").createEntityManager();

        Person p = new Person();
        p.setName("Andi");
        p.setGeburt(new Date());

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();


        TypedQuery<Person> query = em.createNamedQuery("Person.findAll", Person.class);
        List<Person> liste = query.getResultList();
        for(Person pe : liste)
        {
            System.out.println(pe);
        }
        em.close();*/
    }
}
