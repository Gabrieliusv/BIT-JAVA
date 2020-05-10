package lt.bit.addressbookspringdata.controllers;

import java.util.ArrayList;
import lt.bit.addressbookspringdata.dao.ContactDAO;
import lt.bit.addressbookspringdata.dao.PersonDAO;
import lt.bit.addressbookspringdata.data.Contact;
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
@RequestMapping("{personId}/contact")
public class ContactController {
    
    @Autowired
    private PersonDAO personDAO;
     
     @Autowired
    private ContactDAO contactDAO;
    
    @GetMapping
    public ModelAndView list(@PathVariable(value = "personId") Integer personId) {
        ModelAndView mav = new ModelAndView("contactList");
        Person p = personDAO.getOne(personId);
        if(p != null){
            mav.addObject("list", p.getContacts());
        } else {
        mav.addObject("list", new ArrayList<Contact>());
        }
        return mav;
    }
    
    @GetMapping("edit")
    public ModelAndView edit(@PathVariable(value = "personId") Integer personId, @RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView("contactEdit");
        mav.addObject("personId", personId);
        if (id != null) {
            mav.addObject("contact", contactDAO.getOne(id));
        }
        return mav;
    }
    
    @PostMapping("save")
    @Transactional
    public String save(@PathVariable(value = "personId") Integer personId,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "contact") String contact,
            @RequestParam(value = "contactType") String contactType
            ) {
        Contact c;
        if(id == null){
        c = new Contact();
        c.setPerson(personDAO.getOne(personId));
        } else {
        c = contactDAO.getOne(id);
        }
         if(c != null){
          c.setContact(contact);
        c.setContactType(contactType);
        contactDAO.save(c);
         }
       
        return "redirect:./";
    }
    
    @GetMapping("delete")
    @Transactional
    public String delete(@RequestParam(value = "id") Integer id) {
        contactDAO.deleteById(id);
        return "redirect:./";
    }
}
