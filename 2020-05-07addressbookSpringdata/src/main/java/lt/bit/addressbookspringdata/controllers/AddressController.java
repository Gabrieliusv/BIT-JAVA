package lt.bit.addressbookspringdata.controllers;

import java.util.ArrayList;
import lt.bit.addressbookspringdata.dao.AddressDAO;
import lt.bit.addressbookspringdata.dao.PersonDAO;
import lt.bit.addressbookspringdata.data.Address;
import lt.bit.addressbookspringdata.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Gabrielius
 */
@Controller
@RequestMapping("{personId}/address")
public class AddressController {

     @Autowired
    private PersonDAO personDAO;
    
    @Autowired
    private AddressDAO addressDAO;
   
    @GetMapping
    public ModelAndView list(@PathVariable(value = "personId") Integer personId) {
        ModelAndView mav = new ModelAndView("addressList");
        mav.addObject("list", addressDAO.orderByPersonId(personId));
    
        return mav;
    }
    
    @GetMapping("edit")
    public ModelAndView edit(@PathVariable(value = "personId") Integer personId, @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView("addressEdit");
        mav.addObject("personId", personId);
        if (id != null) {
            mav.addObject("address", addressDAO.getOne(id));
        }
        return mav;
    }
    
    @PostMapping("save")
    @Transactional
    public String save(@PathVariable(value = "personId") Integer personId,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "postalCode") String postalCode
    ) {
        Address a;
        if(id == null){
        a = new Address();
        a.setPerson(personDAO.getOne(personId));
        } else {
            a = addressDAO.getOne(id);
        }
        
        if(a != null){
        a.setAddress(address);
        a.setCity(city);
        a.setPostalCode(postalCode);
        addressDAO.save(a);
        }
        
        return "redirect:./";
    }
    
    @GetMapping("delete")
    @Transactional
    public String delete(@RequestParam(value = "id") Integer id) {
        addressDAO.deleteById(id);
        return "redirect:./";
    }
    
}
