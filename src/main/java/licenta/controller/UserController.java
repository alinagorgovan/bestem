package licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = "/")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String register() { return "register"; }

    @GetMapping(value = "/recover")
    public String recover() {
        return "recover";
    }
}
