/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.util.Objects;

/**
 *
 * @author Gabrielius
 */
public class Contact {

    private Integer id;
    private String contact;
    private String type;
    private static int next = 1;

    public Contact() {
        id = next++;
    }

    public static int getNext() {
        return next;
    }

    public Integer getId() {
        return id;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", contact=" + contact + ", type=" + type + '}';
    }

    public String getType() {
        return type;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void setNext(int next) {
        Contact.next = next;
    }
}
