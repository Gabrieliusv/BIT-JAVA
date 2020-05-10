package lt.bit.addressbookspringdata.controllers;

import java.util.List;
import lt.bit.addressbookspringdata.dao.AddressDAO;
import lt.bit.addressbookspringdata.dao.ContactDAO;
import lt.bit.addressbookspringdata.dao.PersonDAO;
import lt.bit.addressbookspringdata.data.Address;
import lt.bit.addressbookspringdata.data.Contact;
import lt.bit.addressbookspringdata.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gabrielius
 */
@RestController
@RequestMapping("/rest/person/{personId}/contact")
public class ContactRESTController {
    
     @Autowired
    private ContactDAO contactDAO;
    
    @Autowired
    private PersonDAO personDAO;

    @GetMapping
    public List<Contact> getContacts(@PathVariable("personId") Integer personId) {
        return contactDAO.findByPersonId(personId);
    }
    
     @GetMapping("{id}")
    public Contact getContact(@PathVariable("personId") Integer personId, @PathVariable("id") Integer id) {
        Contact contact = contactDAO.getOne(id);
         if(contact.getPerson().getId() + 0 == personId + 0){
        return contact;
        } else{
        return null;
        }
    }
    
    @DeleteMapping("{id}")
    @Transactional
    public void deleteContact(@PathVariable("personId") Integer personId, @PathVariable("id") Integer id){
     Contact contact = contactDAO.getOne(id);
         if(contact.getPerson().getId() + 0 == personId + 0){
             contactDAO.deleteById(id);
        }
    }
    
    @PostMapping
    @Transactional
    public Contact addPersonContact(@PathVariable("personId")Integer personId, @RequestBody Contact c){
        Person p = personDAO.getOne(personId);
        c.setPerson(p);
        contactDAO.save(c);
        return c;
    }
    
    @PutMapping("{id}")
    @Transactional
    public Contact updatePersonContact(@PathVariable("personId")Integer personId, @RequestBody Contact c, @PathVariable("id") Integer id){
        Contact original = contactDAO.getOne(id);
        original.setContact(c.getContact());
        original.setContactType(c.getContactType());
       
        return original;
    }
}
