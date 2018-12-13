package ch.skunky.springdemo.controller;

import ch.skunky.springdemo.model.Client;
import ch.skunky.springdemo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable long id){
        Optional<Client> client = clientService.getClient(id);
        if(client.isPresent()) return client.get();
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable long id){
        clientService.deleteClient(id);
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        Client savedClient = clientService.save(client);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClient.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


}
