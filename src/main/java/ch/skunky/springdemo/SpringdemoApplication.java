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
 * look at: Spring WebFlux
 *
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
