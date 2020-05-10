package lt.bit.addressbookspringdata.controllers;

import java.util.List;
import lt.bit.addressbookspringdata.dao.PersonDAO;
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
@RequestMapping("/rest/person")
public class PersonRESTController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping
    public List<Person> list() {
        return personDAO.orderedList();
    }

    @GetMapping("{id}")
    public Person byId(@PathVariable("id") Integer id) {
        return personDAO.getOne(id);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void deleteById(@PathVariable("id") Integer id) {
        personDAO.deleteById(id);
    }

    @PostMapping
    @Transactional
    public Person addPerson(@RequestBody Person p) {
        personDAO.save(p);
        return p;
    }
    
    @PutMapping("{id}")
    @Transactional
    public Person updatePerson(@PathVariable("id") Integer id, @RequestBody Person p) {
        System.out.println(p);
        Person original = personDAO.getOne(id);
        original.setFirstName(p.getFirstName());
        original.setLastName(p.getLastName());
        original.setBirthDate(p.getBirthDate());
        original.setSalary(p.getSalary());
        
        return original;
    }
}
