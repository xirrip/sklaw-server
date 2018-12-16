package ch.skunky.springdemo.controller;

import ch.skunky.springdemo.model.User;
import ch.skunky.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account/*")
public class UserAccountController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/user")
    public List<User> listUser(){
        return userService.findAll();
    }

    @GetMapping(value = "/user/{username}")
    public Optional<User> getOne(@PathVariable(value = "username") String username){
        return userService.getUser(username);
    }

}
