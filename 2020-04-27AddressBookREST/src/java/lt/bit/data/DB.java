package lt.bit.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Gabrielius
 */
public class DB {

    private static List<Person> list = new ArrayList();

    //Person methods
    public static List<Person> getPersons(EntityManager em) {
        Query q = em.createQuery("select p from Person p order by p.firstName");
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

    public static void addPerson(EntityManager em, Person p) {
        if (p != null) {
            p.setId(null);
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
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(p);
            em.flush();
            tx.commit();
        }
    }

    public static void updatePerson(EntityManager em, Person up) {
        if (up != null) {
            if (up.getBirthDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(up.getBirthDate());
                cal.set(Calendar.HOUR, 12);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                up.setBirthDate(cal.getTime());
            }
            if (up.getId() != null) {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                Person p = em.find(Person.class, up.getId());
                if (p != null) {
                    p.setFirstName(up.getFirstName());
                    p.setLastName(up.getLastName());
                    p.setBirthDate(up.getBirthDate());
                    p.setSalary(up.getSalary());
                    em.persist(p);
                    em.flush();
                }
                tx.commit();
            }
        }
    }

    public static void removePerson(EntityManager em, Integer id) {
        if (id != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Person p = em.find(Person.class, id);
            if (p != null) {
                em.remove(p);
                em.flush();
            }
            tx.commit();
        }
    }

    //Addresses methods
    public static List<Address> getPersonAddresses(EntityManager em, Integer id) {
        if (id != null) {
            Person p = em.find(Person.class, id);
            if (p != null) {
                return p.getAddresses();
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    public static Address getAddress(EntityManager em, Integer id) {
        if (id != null) {
            return em.find(Address.class, id);
        } else {
            return null;
        }
    }

    public static void updateAddress(EntityManager em, Address a) {
        if (a != null && a.getId() != null) {

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Address address = getAddress(em, a.getId());
            if (address == null) {
                return;
            }
            address.setAddress(a.getAddress());
            address.setCity(a.getCity());
            address.setPostalCode(a.getPostalCode());
            em.persist(address);
            em.flush();

            tx.commit();
        }
    }

    public static void addPersonAddress(EntityManager em, Integer id, Address a) {
        if (a != null && id != null) {
            a.setId(null);
            EntityTransaction tx = em.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }
            try {
                Person p = em.find(Person.class, id);
                if (p != null) {
                    a.setPerson(p);
                    em.persist(a);
                }
                em.flush();
                tx.commit();
            } catch (Exception ex) {
                if (tx.isActive()) {
                    try {
                        tx.rollback();
                    } catch (Exception e) {
                        // Log error for tx rollback
                    }
                }
                throw ex;
            }
        }
    }

    public static void removeAddress(EntityManager em, Integer id) {
        if (id != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Address a = em.find(Address.class, id);
            if (a != null) {
                em.remove(a);
                em.flush();
            }
            tx.commit();
        }
    }

    //Contact methods
    public static List<Contact> getPersonContacts(EntityManager em, Integer id) {
        if (id != null) {
            Person p = em.find(Person.class, id);
            if (p != null) {
                return p.getContacts();
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    public static Contact getContact(EntityManager em, Integer id) {
        if (id != null) {
            return em.find(Contact.class, id);
        } else {
            return null;
        }
    }

    public static void updateContact(EntityManager em, Contact c) {
        if (c != null && c.getId() != null) {

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Contact contact = em.find(Contact.class, c.getId());

            if (contact != null) {
                contact.setType(c.getType());
                contact.setContact(c.getContact());
                em.persist(contact);
                em.flush();
            }
            tx.commit();
        }
    }

    public static void addPersonContact(EntityManager em, Integer id, Contact c) {
        if (c != null && id != null) {
            c.setId(null);
            Person p = em.find(Person.class, id);
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            if (p != null) {
                c.setPerson(p);
                em.persist(c);
                em.flush();
            }
            tx.commit();
        }
    }

    public static void removeContact(EntityManager em, Integer id) {
        if (id != null) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Contact c = em.find(Contact.class, id);
            if (c != null) {
                em.remove(c);
                em.flush();
            }
            tx.commit();
        }
    }

}
