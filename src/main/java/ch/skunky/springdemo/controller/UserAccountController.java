package ch.skunky.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account/*")
public class UserAccountController {

    @RequestMapping
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup/process")
    public String processSignup(ModelMap model,
                                @RequestParam("nickname") String nickname,
                                @RequestParam("emailaddress") String emailAddress,
                                @RequestParam("password") String password) {
        model.addAttribute("login", true);
        model.addAttribute("nickname", nickname);
        return "index";
    }

    @GetMapping("/forgotpassword")
    public String forgotpassword() {
        return "forgotpassword";
    }

}
