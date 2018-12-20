package ch.skunky.springdemo.controller;

import ch.skunky.springdemo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// TODO need to move to HTTPS
// @CrossOrigin() TODO maybe do fine granular CORS control (only allow credential on this controller)
@RestController
public class LoginController {

    private RemoteTokenServices remoteTokenServices;

    @Autowired
    public LoginController(RemoteTokenServices remoteTokenServices){
        this.remoteTokenServices = remoteTokenServices;
    }

    @PostMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map postLogin(@RequestBody User user, HttpServletResponse response) throws IOException {

        // call auth server
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("fooClientIdPassword", "secret");

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
                map.add("grant_type", "password");
                map.add("username", user.getUsername());
                map.add("password", user.getPassword());
                map.add("client_id", "fooClientIdPassword");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> authResponse = restTemplate.postForEntity("http://localhost:8081/spring-security-oauth-server/oauth/token", request , Map.class );
        Map resultMap = authResponse.getBody();
        System.out.println("Access token = " + resultMap.get("access_token"));

        storeRefreshToken(resultMap, response);

        resultMap.remove("refresh_token");
        return resultMap;
        // encrypt refresh token and add to cookie

    }

    @GetMapping(value = "/auth/refresh")
    public Map postRefresh(HttpServletResponse response, @CookieValue("refresh_token") String refreshToken){
        // TODO decrypt
        // call auth server
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth("fooClientIdPassword", "secret");

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        map.add("client_id", "fooClientIdPassword");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> authResponse = restTemplate.postForEntity("http://localhost:8081/spring-security-oauth-server/oauth/token", request , Map.class );
        Map resultMap = authResponse.getBody();

        storeRefreshToken(resultMap, response);

        System.out.println("Access Token: " + resultMap.get("access_token"));
        System.out.println("Refresh Token: " + resultMap.get("refresh_token"));

        resultMap.remove("refresh_token");
        return resultMap;
    }

    private void storeRefreshToken(Map authResponse, HttpServletResponse response){
        // TODO and encrypt
        // https://www.baeldung.com/java-cipher-input-output-stream

        Cookie refreshTokenCookie = new Cookie("refresh_token", (String) authResponse.get("refresh_token"));
        refreshTokenCookie.setHttpOnly(true);
        // refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setMaxAge(2590000);
        refreshTokenCookie.setPath("/auth"); // does this work?

        response.addCookie(refreshTokenCookie);
    }


    @GetMapping("/auth/checkCookie")
    public void checkCookie(@CookieValue("refresh_token") String refreshToken){
        System.out.println("Refresh token from Cookie = " + refreshToken);
    }

}
