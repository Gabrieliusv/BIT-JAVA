package lt.bit.addressbookmvc.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import lt.bit.addressbookmvc.data.Address;
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
@RequestMapping("{personId}/address")
public class AddressController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ModelAndView list(@PathVariable(value = "personId") Integer personId) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        List<Address> l = DB.getPersonAddresses(em, personId);
        ModelAndView mav = new ModelAndView("addressList");
        mav.addObject("list", l);
        return mav;
    }
    
    @GetMapping("edit")
    public ModelAndView edit(@PathVariable(value = "personId") Integer personId, @RequestParam(value = "id", required = false) Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        ModelAndView mav = new ModelAndView("addressEdit");
        mav.addObject("personId", personId);
        if (id != null) {
            mav.addObject("address", DB.getAddress(em, id));
        }
        return mav;
    }
    
    @PostMapping("save")
    public String save(@PathVariable(value = "personId") Integer personId,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "postalCode") String postalCode
    ) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Address a = new Address();
        a.setAddress(address);
        a.setCity(city);
        a.setPostalCode(postalCode);
        
        if (id == null) {
            DB.addPersonAddress(em, personId, a);
        } else {
            a.setId(id);
            DB.updateAddress(em, a);
        }
        return "redirect:./";
    }
    
    @GetMapping("delete")
    public String delete(@RequestParam(value = "id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.deleteAddress(em, id);
        return "redirect:./";
    }
    
}
