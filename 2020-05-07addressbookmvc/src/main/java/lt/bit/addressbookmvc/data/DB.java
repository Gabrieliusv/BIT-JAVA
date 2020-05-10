package lt.bit.addressbookmvc.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class DB {

    //Person methods
    public static List<Person> getPersons(EntityManager em, String filter) {
        Query q;
        if (filter != null) {
            q = em.createQuery("select p from Person p where p.firstName like concat('%', :filter, '%') or p.lastName like concat('%', :filter, '%') order by p.firstName");
            q.setParameter("filter", filter);
        } else {
            q = em.createQuery("select p from Person p order by p.firstName");
        }
        List<Person> l = q.getResultList();
        return l;
    }

    public static Person getPerson(EntityManager em, Integer id) {
        if (id != null) {
            return em.find(Person.class, id);
        } else {
            return null;
        }
    }

    public static Person addPerson(EntityManager em, Person p) {
        if (p == null) {
            return null;
        }
        p.setId(null);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            if (p.getBirthDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(p.getBirthDate());
                cal.set(Calendar.HOUR, 12);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                java.sql.Date bd = new java.sql.Date(cal.getTimeInMillis());
                p.setBirthDate(cal.getTime());
            }
            em.persist(p);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        return p;

    }

    public static Person updatePerson(EntityManager em, Person p) {
        if (p == null || p.getId() == null) {
            return null;
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            if (p.getBirthDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(p.getBirthDate());
                cal.set(Calendar.HOUR, 12);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                p.setBirthDate(cal.getTime());
            }

            Person original = em.find(Person.class, p.getId());

            if (original != null) {
                original.setFirstName(p.getFirstName());
                original.setLastName(p.getLastName());
                original.setBirthDate(p.getBirthDate());
                original.setSalary(p.getSalary());
                em.persist(original);
                em.flush();
            }
            tx.commit();
            return original;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

    }

    public static Person deletePerson(EntityManager em, Integer id) {
        if (id == null) {
            return null;
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Person p = em.find(Person.class, id);
            if (p != null) {
                em.remove(p);
            }
            tx.commit();
            return p;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

    }

//Addresses methods
    public static List<Address> getPersonAddresses(EntityManager em, Integer id) {
        if (id == null) {
            return new ArrayList<>();
        }
        Person p = em.find(Person.class,
                id);
        if (p != null) {
            return p.getAddresses();
        } else {
            return new ArrayList<>();
        }

    }

    public static Address getAddress(EntityManager em, Integer id) {
        if (id == null) {
            return null;
        }
        return em.find(Address.class, id);
    }

    public static Address updateAddress(EntityManager em, Address a) {
        if (a == null || a.getId() == null) {
            return null;
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Address original = em.find(Address.class, a.getId());

            if (original != null) {
                original.setAddress(a.getAddress());
                original.setCity(a.getCity());
                original.setPostalCode(a.getPostalCode());
                em.persist(original);
                em.flush();
            }
            tx.commit();
            return original;

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

    }

    public static Address addPersonAddress(EntityManager em, Integer personId, Address a) {
        if (a == null || personId == null) {
            return null;
        }
        a.setId(null);
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Person p = em.find(Person.class, personId);
            if (p != null) {
                a.setPerson(p);
                em.persist(a);
            }
            em.flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw ex;
        }
        return a;
    }

    public static Address deleteAddress(EntityManager em, Integer id) {
        if (id == null) {
            return null;
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Address a = em.find(Address.class, id);
            if (a != null) {
                em.remove(a);
            }
            tx.commit();
            return a;

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

    }

    //Contact methods
    public static List<Contact> getPersonContacts(EntityManager em, Integer id) {
        if (id == null) {
            return new ArrayList<>();
        }
        Person p = em.find(Person.class,
                id);
        if (p != null) {
            return p.getContacts();
        } else {
            return new ArrayList<>();
        }

    }

    public static Contact getContact(EntityManager em, Integer id) {
        if (id == null) {
            return null;
        }
        return em.find(Contact.class,
                id);

    }

    public static Contact updateContact(EntityManager em, Contact c) {
        if (c == null || c.getId() == null) {
            return null;
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Contact original = em.find(Contact.class, c.getId());

            if (original != null) {
                original.setContactType(c.getContactType());
                original.setContact(c.getContact());
                em.persist(original);
                em.flush();
            }
            tx.commit();
            return original;

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

    }

    public static Contact addPersonContact(EntityManager em, Integer personId, Contact c) {
        if (c == null || personId == null) {
            return null;
        }
        c.setId(null);
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Person p = em.find(Person.class, personId);
            if (p != null) {
                c.setPerson(p);
                em.persist(c);
            }
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        return c;
    }

    public static Contact deleteContact(EntityManager em, Integer id) {
        if (id == null) {
            return null;
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Contact c = em.find(Contact.class, id);
            if (c != null) {
                em.remove(c);
            }
            tx.commit();
            return c;

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

    }

    
}
