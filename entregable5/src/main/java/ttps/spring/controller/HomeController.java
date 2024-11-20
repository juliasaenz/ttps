package ttps.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/") // This maps the root URL to this method
    public String showHomePage() {
        return "home"; // This is the name of the JSP file (without the .jsp extension)
    }
}
