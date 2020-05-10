package lt.bit.addressbookmvc.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import lt.bit.addressbookmvc.data.DB;
import lt.bit.addressbookmvc.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
    
    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView list(@RequestParam(value = "filter", required = false)String filter) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        List<Person> l = DB.getPersons(em, filter);
        ModelAndView mav = new ModelAndView("personList");
        mav.addObject("list", l);
        return mav;
    }
    
    @GetMapping("edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        ModelAndView mav = new ModelAndView("personEdit");
        if (id != null) {
            mav.addObject("person", DB.getPerson(em, id));
        }
        return mav;
    }
    
    @PostMapping("save")
    public String save(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "birthDate") String birthDate,
            @RequestParam(value = "salary") BigDecimal salary
    ) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Person p = new Person();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setSalary(salary);
        try {
            p.setBirthDate(sdf.parse(birthDate));
        } catch (Exception e) {
            //ignored
        }
        
        if (id == null) {
            DB.addPerson(em, p);
        } else {
            p.setId(id);
            DB.updatePerson(em, p);
        }
        return "redirect:./";
    }
    
    @GetMapping("delete")
    public String delete(@RequestParam(value = "id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.deletePerson(em, id);
        return "redirect:./";
    }
    
}
