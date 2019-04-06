package licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping(value = "/loggedIn/home")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @GetMapping(value = "/info")
    public String info() {
        return "info";
    }

//    @GetMapping(value = "/merge")
//    public String redirectEverythingOtherThanTest(){
//        return "redirect:/loginPage";
//    }
}
