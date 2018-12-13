package ch.skunky.springdemo.controller;

import ch.skunky.springdemo.model.Client;
import ch.skunky.springdemo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientSearchController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value="/clients", method= RequestMethod.GET,
            produces="application/json")
    public List<Client> searchClient(
            @RequestParam(value="name", required=false) String name)
    {
        if(name!=null){
            return clientService.findClientLike(name);
        }
        else{
            return clientService.findAll();
        }
    }



}
