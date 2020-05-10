package lt.bit.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mano")
public class FirstController {

    @GetMapping
    public String test() {
        return "index";
    }

    @RequestMapping(value = "vardas", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView suVardu(@RequestParam(value = "vardas", required = false) String v) {
        ModelAndView mav = new ModelAndView("vardas");
        
        if(v != null){
        mav.addObject("v", v);
        } else {
         mav.addObject("v", "Petras");
        }
        mav.addObject("p", "Petraitis");
        
        return mav;
    }
}
