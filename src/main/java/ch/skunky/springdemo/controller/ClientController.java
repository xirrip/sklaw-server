package ch.skunky.springdemo.controller;

import ch.skunky.springdemo.model.Client;
import ch.skunky.springdemo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @CrossOrigin
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

    @CrossOrigin
    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client, @PathVariable long id) {

        Optional<Client> clientOptional = clientService.getClient(id);

        if (!clientOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        client.setId(id);

        clientService.save(client);

        return ResponseEntity.noContent().build();
    }



}
