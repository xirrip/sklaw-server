package ch.skunky.skunklaw.config;

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
 * https://www.baeldung.com/security-spring
 *
 * maybe do an OpenID on top:
 * https://www.baeldung.com/spring-security-openid-connect
 * -> then angular gets easier, maybe? -> no, will require google account to login
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
 *
 * practical notes:
 * if nothing else pops up, move the get access token to API side, and only return the token.
 * needs HTTPS?!: https://drissamri.be/blog/java/enable-https-in-spring-boot/
 *
 * this could be a way around using client secret: https://jeremymarc.github.io/2014/08/14/oauth2-with-angular-the-right-way
 *
 */
@SpringBootApplication
@EntityScan(basePackages = {"ch.skunky.skunklaw.model"})
@ComponentScan(basePackages = {"ch.skunky.skunklaw.config", "ch.skunky.skunklaw.controller",
        "ch.skunky.skunklaw.service",  })
@EnableJpaRepositories("ch.skunky.skunklaw.repository")
public class SpringdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }
}
