/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gabrielius
 */
public class DB {

    private static List<Person> list = new ArrayList<>();

    static {
        Person p = new Person();
        p.setFirstName("Jonas");
        p.setLastName("Jonaitis");
        p.setBirthDate(new Date());
        p.setSalary(new BigDecimal("123.45"));

        List<Address> al = new ArrayList<>();
        Address a = new Address();
        a.setAddress("Tauro g. 15");
        a.setCity("Vilnius");
        a.setPostalCode("08456");
        al.add(a);
        a = new Address();
        a.setAddress("Ateities g. 15");
        a.setCity("Vilnius");
        a.setPostalCode("08226");
        al.add(a);
        a = new Address();
        a.setAddress("Kalvarijų g. 15");
        a.setCity("Vilnius");
        a.setPostalCode("08526");
        al.add(a);
        p.setAdresses(al);

        List<Contact> cl = new ArrayList<>();
        Contact c = new Contact();
        c.setContact("+3705885256");
        c.setType("Namu");
        cl.add(c);
        c = new Contact();
        c.setContact("+3706885256");
        c.setType("Mobilus");
        cl.add(c);
        p.setContacts(cl);

        list.add(p);
        p = new Person();

        p.setFirstName("Petras");
        p.setLastName("Petraitis");
        p.setBirthDate(new Date());
        p.setSalary(new BigDecimal("245.88"));
        
        al = new ArrayList<>();
        a = new Address();
        a.setAddress("Žalgirio g. 20");
        a.setCity("Vilnius");
        a.setPostalCode("08466");
        al.add(a);
        a = new Address();
        a.setAddress("Žirgo g. 26");
        a.setCity("Vilnius");
        a.setPostalCode("08556");
        al.add(a);
        a = new Address();
        a.setAddress("Savanorių g. 15");
        a.setCity("Vilnius");
        a.setPostalCode("08526");
        al.add(a);
        p.setAdresses(al);

        cl = new ArrayList<>();
        c = new Contact();
        c.setContact("+3702235556");
        c.setType("Namu");
        cl.add(c);
        c = new Contact();
        c.setContact("+3706526556");
        c.setType("Mobilus");
        cl.add(c);
        p.setContacts(cl);

        list.add(p);
    }

    //Addresses methods
    public static List<Address> getPersonAddresses(Integer id) {
        Person p = getPerson(id);
        return (p != null) ? p.getAdresses() : null;
    }

    public static Address getAddress(Integer id) {
        if (id == null) {
            return null;
        }
        for (Person p : getPersons()) {
            for (Address a : p.getAdresses()) {
                if (id.equals(a.getId())) {
                    return a;
                }
            }
        }
        return null;
    }

    public static void removeAddress(Integer id) {
        if (id == null) {
            return;
        }
        for (Person p : getPersons()) {
            for (Address a : p.getAdresses()) {
                if (id.equals(a.getId())) {
                    p.getAdresses().remove(a);
                    return;
                }
            }
        }
    }

    public static void updateAddress(Address ua) {
        if (ua == null || ua.getId() == null) {
            return;
        }
        Address a = getAddress(ua.getId());
        if (a == null) {
            return;
        }
        a.setAddress(ua.getAddress());
        a.setCity(ua.getCity());
        a.setPostalCode(ua.getPostalCode());
    }

    public static void addPersonAddress(Integer id, Address a) {
        Person p = getPerson(id);
        if (p == null) {
            return;
        }
        p.getAdresses().add(a);
    }

    //Contact methods
    public static List<Contact> getPersonContacts(Integer id) {
        Person p = getPerson(id);
        return (p != null) ? p.getContacts() : null;
    }

    public static Contact getContact(Integer id) {
        if (id == null) {
            return null;
        }
        for (Person p : getPersons()) {
            for (Contact a : p.getContacts()) {
                if (id.equals(a.getId())) {
                    return a;
                }
            }
        }
        return null;
    }

    public static void removeContact(Integer id) {
        if (id == null) {
            return;
        }
        for (Person p : getPersons()) {
            for (Contact a : p.getContacts()) {
                if (id.equals(a.getId())) {
                    p.getContacts().remove(a);
                    return;
                }
            }
        }
    }

    public static void updateContact(Contact uc) {
        if (uc == null || uc.getId() == null) {
            return;
        }
        Contact c = getContact(uc.getId());
        if (c == null) {
            return;
        }
        c.setContact(uc.getContact());
        c.setType(uc.getType());
    }

    public static void addPersonContact(Integer id, Contact c) {
        Person p = getPerson(id);
        if (p == null) {
            return;
        }
        p.getContacts().add(c);
    }

    //Person methods
    public static List<Person> getPersons() {
        return list;
    }

    public static void addPerson(Person p) {
        if (p != null && !list.contains(p)) {
            list.add(p);
        }
    }

    public static void removePerson(Integer id) {
        if (id == null) {
            return;
        }
        for (Person p : list) {
            if (id.equals(p.getId())) {
                list.remove(p);
                return;
            }
        }
    }

    public static Person getPerson(Integer id) {
        if (id == null) {
            return null;
        }
        for (Person p : list) {
            if (id.equals(p.getId())) {
                return p;
            }
        }
        return null;
    }

    public static void updatePerson(Person up) {
        if (up == null || up.getId() == null) {
            return;
        }
        for (Person p : list) {
            if (up.getId().equals(p.getId())) {
                p.setFirstName(up.getFirstName());
                p.setLastName(up.getLastName());
                p.setBirthDate(up.getBirthDate());
                p.setSalary(up.getSalary());
                return;
            }
        }
    }

}
