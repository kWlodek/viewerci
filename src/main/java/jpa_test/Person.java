package jpa_test;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Nico on 23.02.2018.
 */

@Entity
@Table(name="personen")
@NamedQueries({
        @NamedQuery(name="Person.findAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name ="Person.findByName", query = "SELECT p FROM Person p WHERE p.name LIKE :n")
})
public class Person
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date geburt;

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", geburt=" + geburt +
                '}';
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setGeburt(Date geburt)
    {
        this.geburt = geburt;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Date getGeburt()
    {
        return geburt;
    }
}
