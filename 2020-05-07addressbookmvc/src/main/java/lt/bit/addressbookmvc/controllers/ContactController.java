package lt.bit.addressbookmvc.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import lt.bit.addressbookmvc.data.Contact;
import lt.bit.addressbookmvc.data.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private HttpServletRequest request;
    
    @GetMapping
    public ModelAndView list(@PathVariable(value = "personId") Integer personId) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        List<Contact> l = DB.getPersonContacts(em, personId);
        ModelAndView mav = new ModelAndView("contactList");
        mav.addObject("list", l);
        return mav;
    }
    
    @GetMapping("edit")
    public ModelAndView edit(@PathVariable(value = "personId") Integer personId, @RequestParam(value = "id", required = false) Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        ModelAndView mav = new ModelAndView("contactEdit");
        mav.addObject("personId", personId);
        if (id != null) {
            mav.addObject("contact", DB.getContact(em, id));
        }
        return mav;
    }
    
    @PostMapping("save")
    public String save(@PathVariable(value = "personId") Integer personId,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "contact") String contact,
            @RequestParam(value = "contactType") String contactType
            ) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Contact c = new Contact();
        c.setContact(contact);
        c.setContactType(contactType);
        
        if (id == null) {
            DB.addPersonContact(em, personId, c);
        } else {
            c.setId(id);
            DB.updateContact(em, c);
        }
        return "redirect:./";
    }
    
    @GetMapping("delete")
    public String delete(@RequestParam(value = "id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.deleteContact(em, id);
        return "redirect:./";
    }
}
