package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.model.User;
import ch.skunky.skunklaw.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/account/*")
public class UserAccountController {

    public static final String USER_REGISTER = "http://localhost:8081/spring-security-oauth-server/user/register";
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

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(value="/user/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@RequestHeader("Authorization") String authHeader, @RequestBody User user) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Authorization", authHeader);

        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("password", user.getPassword());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(userMap);

        HttpEntity <String> httpEntity = new HttpEntity <String> (objectMapper.writeValueAsString(userMap), httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(USER_REGISTER, httpEntity, String.class);
        Map resultMap = objectMapper.readValue(response, Map.class);

        return new User((String) resultMap.get("username"), null, (String) resultMap.get("email"));
    }

    @PostMapping(value="/user/registeradminuser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registerAdminUser(String authHeader, @RequestBody User user) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("password", user.getPassword());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(userMap);

        HttpEntity <String> httpEntity = new HttpEntity <String> (objectMapper.writeValueAsString(userMap), httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(USER_REGISTER, httpEntity, String.class);
        Map resultMap = objectMapper.readValue(response, Map.class);

        return new User((String) resultMap.get("username"), null, (String) resultMap.get("email"));
    }


}
