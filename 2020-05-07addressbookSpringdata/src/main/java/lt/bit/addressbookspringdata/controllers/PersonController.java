package lt.bit.addressbookspringdata.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import lt.bit.addressbookspringdata.dao.PersonDAO;
import lt.bit.addressbookspringdata.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private PersonDAO personDAO;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView list(@RequestParam(value = "filter", required = false) String filter) {
        List<Person> l;
        if(filter == null){
         l = personDAO.orderedList();
        } else {
        l = personDAO.filteredList(filter);
        }
        ModelAndView mav = new ModelAndView("personList");
        mav.addObject("list", l);
        return mav;
    }

    @GetMapping("edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView("personEdit");
        if (id != null) {
            mav.addObject("person", personDAO.getOne(id));
        }
        return mav;
    }

    @PostMapping("save")
    @Transactional
    public String save(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "birthDate") String birthDate,
            @RequestParam(value = "salary") BigDecimal salary
    ) {

        Person p = new Person();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setSalary(salary);
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(birthDate));
            c.set(Calendar.HOUR, 12);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            p.setBirthDate(c.getTime());
        } catch (Exception e) {
            //ignored
        }

        p.setId(id);
        personDAO.save(p);
        return "redirect:./";
    }
    
    @GetMapping("delete")
    @Transactional
    public String delete(@RequestParam(value = "id") Integer id) {
        personDAO.deleteById(id);
        return "redirect:./";
    }
    
}
