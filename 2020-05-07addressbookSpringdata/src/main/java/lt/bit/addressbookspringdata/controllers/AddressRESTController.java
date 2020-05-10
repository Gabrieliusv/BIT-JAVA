package lt.bit.addressbookspringdata.controllers;

import java.util.List;
import javax.websocket.server.PathParam;
import lt.bit.addressbookspringdata.dao.AddressDAO;
import lt.bit.addressbookspringdata.dao.PersonDAO;
import lt.bit.addressbookspringdata.data.Address;
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
@RequestMapping("/rest/person/{personId}/address")
public class AddressRESTController {
    
    @Autowired
    private AddressDAO addressDAO;
    
    @Autowired
    private PersonDAO personDAO;

    @GetMapping
    public List<Address> getAddresses(@PathVariable("personId") Integer personId) {
        return addressDAO.orderByPersonId(personId);
    }

    @GetMapping("{id}")
    public Address getAddress(@PathVariable("personId") Integer personId, @PathVariable("id") Integer id) {
        Address address = addressDAO.getOne(id);
         if(address.getPerson().getId() + 0 == personId + 0){
        return address;
        } else{
        return null;
        }
    }
    
    @DeleteMapping("{id}")
    @Transactional
    public void deleteAddress(@PathVariable("personId") Integer personId, @PathVariable("id") Integer id){
    Address address = addressDAO.getOne(id);
         if(address.getPerson().getId() + 0 == personId + 0){
             addressDAO.deleteById(id);
        }
    }
    
    @PostMapping
    @Transactional
    public Address addPersonAddress(@PathVariable("personId")Integer personId, @RequestBody Address a){
        Person p = personDAO.getOne(personId);
        a.setPerson(p);
        addressDAO.save(a);
        return a;
    }
    
    @PutMapping("{id}")
    @Transactional
    public Address updatePersonAddress(@PathVariable("personId")Integer personId, @RequestBody Address a, @PathVariable("id") Integer id){
        Address original = addressDAO.getOne(id);
        original.setAddress(a.getAddress());
        original.setCity(a.getCity());
        original.setPostalCode(a.getPostalCode());
       
        return original;
    }
}
