package ch.skunky.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

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
 *
 * java + docker (+ online docker server?)
 * https://codefresh.io/docker-tutorial/java_docker_pipeline/
 */

@Controller
@SpringBootApplication
@EntityScan(basePackages = {"ch.skunky.springdemo.controller", "ch.skunky.springdemo.model"})
public class SpringdemoApplication {

    private final String message = "Hello Markus";

    @RequestMapping(value="/", method= RequestMethod.GET, produces= MediaType.TEXT_HTML_VALUE)
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "index";
    }

    @RequestMapping(value="/lucky", method= RequestMethod.GET)
    public ModelAndView lucky(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return new ModelAndView("lucky", model);
    }


    @ResponseBody
    @RequestMapping("/world")
    String home() {
        return "Hello world. How are you?";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }
}
