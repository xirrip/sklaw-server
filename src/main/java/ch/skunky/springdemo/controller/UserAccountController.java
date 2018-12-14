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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /*
    @PostMapping(value="/login")
    public ExecutionStatus processLogin(@RequestBody User reqUser, HttpServletRequest request) {
        Authentication authentication = null;
        UsernamePasswordAuthenticationToken token = new         UsernamePasswordAuthenticationToken(reqUser.getEmail(),         reqUser.getPassword());
        try {
            //
            // Delegate authentication check to a custom
            // Authentication provider
            //
            // authentication = this.authenticationProvider.authenticate(token);
            //
            // Store the authentication object in
            // SecurityContextHolder
            // SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            user.setPassword(null);
            return new ExecutionStatus("USER_LOGIN_SUCCESSFUL", "Login Successful!", user);
        } catch (BadCredentialsException e) {
            return new ExecutionStatus("USER_LOGIN_UNSUCCESSFUL", "Username or password is incorrect. Please try again!");
        }
    }
    */

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
