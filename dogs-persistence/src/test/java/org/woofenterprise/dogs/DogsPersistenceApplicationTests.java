package org.woofenterprise.dogs;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.woofenterprise.dogs.entity.Appointment;
import org.woofenterprise.dogs.entity.Customer;
import org.woofenterprise.dogs.entity.Dog;
import org.woofenterprise.dogs.utils.Address;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DogsPersistenceApplication.class)
public class DogsPersistenceApplicationTests {

    @Inject
    EntityManagerFactory emf;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testEntity() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Customer c = new Customer();
        c.setName("Jane");
        c.setSurname("Smith");
        c.setAddress(new Address.Builder().setFirstLine("5th Avenue").setCity("New York").setCode("12345").setCountry("USA").build());
        em.persist(c);

        Dog d = new Dog();
        d.setName("Woofie");
        d.setOwner(c);
        em.persist(d);

        Appointment a = new Appointment();
        a.setCustomer(c);
        a.setDog(d);
        Date startDate = new Date(100);
        Date endDate = new Date(1000);
        a.setStartTime(startDate);
        a.setEndTime(endDate);
        em.persist(a);

        em.getTransaction().commit();
        em.close();
    }

}