/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Gabrielius
 */
public class DB {

    //Person methods

    public static List<Person> getPersons(Connection conn) {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from person order by first_name")) {
            List<Person> l = new ArrayList<>();
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setBirthDate(rs.getDate("birth_date"));
                p.setSalary(rs.getBigDecimal("salary"));
                l.add(p);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void addPerson(Connection conn, Person p) {
        try (PreparedStatement st = conn.prepareStatement("insert into person (first_name, last_name, birth_date, salary) values (?, ?, ?, ?)");) {
            st.setString(1, p.getFirstName());
            st.setString(2, p.getLastName());
            if (p.getBirthDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(p.getBirthDate());
                cal.set(Calendar.HOUR, 12);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                java.sql.Date bd = new java.sql.Date(cal.getTimeInMillis());
                st.setDate(3, bd);
            } else {
                st.setNull(3, Types.DATE);
            }
            if (p.getSalary() != null) {
                st.setBigDecimal(4, p.getSalary());
            } else {
                st.setNull(4, Types.DECIMAL);
            }
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void removePerson(Connection conn, Integer id) {
        try (PreparedStatement st = conn.prepareStatement("delete from person where id = ?");) {
            st.setInt(1, id);
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Person getPerson(Connection conn, Integer id) {
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from person where id = " + id);) {
            if (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setBirthDate(rs.getDate("birth_date"));
                p.setSalary(rs.getBigDecimal("salary"));
                return p;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void updatePerson(Connection conn, Person p) {
        try (PreparedStatement st = conn.prepareStatement("update person set first_name = ?, last_name = ?, birth_date = ?, salary = ? where id = ?");) {
            st.setString(1, p.getFirstName());
            st.setString(2, p.getLastName());
            if (p.getBirthDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(p.getBirthDate());
                cal.set(Calendar.HOUR, 12);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                java.sql.Date bd = new java.sql.Date(cal.getTimeInMillis());
                st.setDate(3, bd);
            } else {
                st.setNull(3, Types.DATE);
            }
            if (p.getSalary() != null) {
                st.setBigDecimal(4, p.getSalary());
            } else {
                st.setNull(4, Types.DECIMAL);
            }
            st.setInt(5, p.getId());
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //Addresses methods

    public static List<Address> getPersonAddresses(Connection conn, Integer id) {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from address where person_id =" + id)) {
            List<Address> l = new ArrayList<>();
            while (rs.next()) {
                Address a = new Address();
                a.setId(rs.getInt("id"));
                a.setAddress(rs.getString("address"));
                a.setCity(rs.getString("city"));
                a.setPostalCode(rs.getString("postal_code"));
                l.add(a);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Address getAddress(Connection conn, Integer id) {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from address where id =" + id)) {
            rs.next();
            Address a = new Address();
            a.setId(rs.getInt("id"));
            a.setAddress(rs.getString("address"));
            a.setCity(rs.getString("city"));
            a.setPostalCode(rs.getString("postal_code"));

            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateAddress(Connection conn, Integer id, Address a) {
        if (a == null || a.getId() == null || id == null) {
            return;
        }
        try (PreparedStatement st = conn.prepareStatement("update address set address = ?, city = ?, postal_code = ? where id = ? && person_id = ?");) {
            if (a.getAddress() != null) {
                st.setString(1, a.getAddress());
            } else {
                st.setNull(1, Types.VARCHAR);
            }
            if (a.getCity() != null) {
                st.setString(2, a.getCity());
            } else {
                st.setNull(2, Types.VARCHAR);
            }
            if (a.getPostalCode() != null) {
                st.setString(3, a.getPostalCode());
            } else {
                st.setNull(3, Types.VARCHAR);
            }
            st.setInt(4, a.getId());
            st.setInt(5, id);
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addPersonAddress(Connection conn, Integer id, Address a) {
         if (a == null || id == null) {
            return;
        }
         
        try (PreparedStatement st = conn.prepareStatement("insert into address (person_id, address, city, postal_code) values (?, ?, ?, ?)");) {
            st.setInt(1, id);
            if (a.getAddress() != null) {
                st.setString(2, a.getAddress());
            } else {
                st.setNull(2, Types.VARCHAR);
            }
            if (a.getCity() != null) {
                st.setString(3, a.getCity());
            } else {
                st.setNull(3, Types.VARCHAR);
            }
            if (a.getPostalCode() != null) {
                st.setString(4, a.getPostalCode());
            } else {
                st.setNull(4, Types.VARCHAR);
            }
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
      public static void removeAddress(Connection conn, Integer id) {
        try (PreparedStatement st = conn.prepareStatement("delete from address where id = ?");) {
            st.setInt(1, id);
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

      //Contact methods

    public static List<Contact> getPersonContacts(Connection conn, Integer id) {
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from contact where person_id =" + id)) {
            List<Contact> l = new ArrayList<>();
            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt("id"));
                c.setType(rs.getString("contact_type"));
                c.setContact(rs.getString("contact"));
                l.add(c);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Contact getContact(Connection conn, Integer id) {
        if (id == null) {
            return null;
        }
        try (Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from contact where id =" + id)) {
            rs.next();
            Contact c = new Contact();
            c.setId(rs.getInt("id"));
            c.setContact(rs.getString("contact"));
            c.setType(rs.getString("contact_type"));

            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateContact(Connection conn, Integer id, Contact c) {
        if (c == null || c.getId() == null || id == null) {
            return;
        }
        try (PreparedStatement st = conn.prepareStatement("update contact set contact_type = ?, contact = ? where id = ? && person_id = ?");) {
            if (c.getType() != null) {
                st.setString(1, c.getType());
            } else {
                st.setNull(1, Types.VARCHAR);
            }
            if (c.getContact() != null) {
                st.setString(2, c.getContact());
            } else {
                st.setNull(2, Types.VARCHAR);
            }
            st.setInt(3, c.getId());
            st.setInt(4, id);
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addPersonContact(Connection conn, Integer id, Contact c) {
        try (PreparedStatement st = conn.prepareStatement("insert into contact (person_id, contact_type, contact) values (?, ?, ?)");) {
            st.setInt(1, id);
            if (c.getType() != null) {
                st.setString(2, c.getType());
            } else {
                st.setNull(2, Types.VARCHAR);
            }
            if (c.getContact() != null) {
                st.setString(3, c.getContact());
            } else {
                st.setNull(3, Types.VARCHAR);
            }
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void removeContact(Connection conn, Integer id) {
        if (id == null) {
            return;
        }
        try (PreparedStatement st = conn.prepareStatement("delete from contact where id = ?");) {
            st.setInt(1, id);
            st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
