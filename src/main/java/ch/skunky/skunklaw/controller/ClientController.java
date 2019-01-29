package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.dto.law.LawClientDto;
import ch.skunky.skunklaw.dto.law.service.LawDtoMappingService;
import ch.skunky.skunklaw.model.law.LawClient;
import ch.skunky.skunklaw.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClientController {

    final private ClientService clientService;
    final private LawDtoMappingService lawDtoMappingService;

    @Autowired
    public ClientController(ClientService clientService, LawDtoMappingService lawDtoMappingService) {
        this.clientService = clientService;
        this.lawDtoMappingService = lawDtoMappingService;
    }

    @PreAuthorize("hasAuthority('write')")
    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable long id){
        clientService.deleteClient(id);
    }


    @PreAuthorize("hasAuthority('write')")
    @PostMapping("/clients")
    public ResponseEntity<LawClientDto> createClient(@RequestBody LawClientDto lawClient) {
        LawClient savedClient = clientService.save(lawDtoMappingService.fromDto(lawClient));
        /*
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClient.getId()).toUri();

        // return type: ResponseEntity<Object>
        return ResponseEntity.created(location).build();
        */
        return ResponseEntity.ok(lawDtoMappingService.toDto(savedClient));
    }

    @PreAuthorize("hasAuthority('write')")
    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody LawClientDto lawClientDto, @PathVariable long id) {

        Optional<LawClient> clientOptional = clientService.getClient(id);

        if (!clientOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        LawClient lawClient = lawDtoMappingService.fromDto(lawClientDto);
        lawClient.setId(id);
        clientService.save(lawClient);

        return ResponseEntity.noContent().build();
    }

}
