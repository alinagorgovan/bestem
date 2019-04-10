package licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/loggedIn")
@Controller
public class HomeController {


    @GetMapping(value = "/home")
    public String home() { return "index"; }

    @GetMapping(value = "/info")
    public String info() {
        return "info";
    }
}
