package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account/*")
public class UserAccountController {

    private static final String USER_REGISTER = "http://localhost:8081/spring-security-oauth-server/user/register";
    private static final String USER_REGISTER_ADMIN = "http://localhost:8081/spring-security-oauth-server/user/registeradminuser";

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(value="/user/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@RequestHeader("Authorization") String authHeader, @RequestBody User user) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Authorization", authHeader);

        return callUserApi(user, httpHeaders, USER_REGISTER);
    }

    @PostMapping(value="/user/registeradminuser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registerAdminUser(@RequestBody User user) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.setBasicAuth("fooClientIdPassword", "secret");

        return callUserApi(user, httpHeaders, USER_REGISTER_ADMIN);
    }

    private User callUserApi(@RequestBody User user, HttpHeaders httpHeaders, String userRegister) throws IOException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("password", user.getPassword());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(userMap);

        HttpEntity<String> httpEntity = new HttpEntity<String>(objectMapper.writeValueAsString(userMap), httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(userRegister, httpEntity, String.class);
        Map resultMap = objectMapper.readValue(response, Map.class);

        return new User((String) resultMap.get("username"), null, (String) resultMap.get("email"));
    }


}
