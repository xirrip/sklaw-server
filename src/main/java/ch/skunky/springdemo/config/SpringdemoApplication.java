package ch.skunky.springdemo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Documentation
 * Docker: https://docs.docker.com/get-started/
 * Docker Hub: xirip beatrice
 * https://github.com/wsargent/docker-cheat-sheet
 *
 * The Book
 * https://www.safaribooksonline.com/library/view/building-web-apps/9781787284661/1239259b-00a8-40e9-8535-cfc17c6d06d9.xhtml
 * Video
 * https://www.safaribooksonline.com/videos/the-complete-react/9781789950656/9781789950656-video1_1?autoplay=false
 *
 * look at: Spring WebFlux
 *
 * Security:
 * consider JWT authentication (instead of Session / Cookies)
 * https://medium.com/@xoor/jwt-authentication-service-44658409e12c
 *
 * java + docker (+ online docker server?)
 * https://codefresh.io/docker-tutorial/java_docker_pipeline/
 *
 *
 * CURRENT SECURITY GUIDE:
 *
 * READ FIRST:
 * https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 * https://chariotsolutions.com/blog/post/angular-2-spring-boot-jwt-cors_part2/
 *
 * https://www.baeldung.com/rest-api-spring-oauth2-angularjs
 * https://www.baeldung.com/spring-security-oauth2-refresh-token-angular-js
 * https://www.baeldung.com/spring-security-oauth-jwt
 *
 * and continuing from here (the angular part is based on that)
 * https://www.devglan.com/spring-security/angular-jwt-authentication
 * https://www.devglan.com/spring-security/spring-boot-jwt-auth
 * https://www.devglan.com/spring-security/jwt-role-based-authorization
 * https://www.devglan.com/spring-security/spring-boot-oauth2-jwt-example
 *
 */
@SpringBootApplication
@EntityScan(basePackages = {"ch.skunky.springdemo.model"})
@ComponentScan(basePackages = { "ch.skunky.springdemo.config", "ch.skunky.springdemo.controller",
        "ch.skunky.springdemo.service",  })
@EnableJpaRepositories("ch.skunky.springdemo.repository")
public class SpringdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }
}
