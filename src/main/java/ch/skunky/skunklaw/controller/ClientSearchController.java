package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.dto.law.LawClientDto;
import ch.skunky.skunklaw.dto.law.service.LawDtoMappingService;
import ch.skunky.skunklaw.model.law.LawClient;
import ch.skunky.skunklaw.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * https://spring.io/guides/gs/rest-service-cors/
 * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
 * https://www.baeldung.com/spring-cors
 *
 */
@RestController
public class ClientSearchController {

    final private ClientService clientService;
    final private LawDtoMappingService mappingService;

    @Autowired
    public ClientSearchController(ClientService clientService, LawDtoMappingService mappingService) {
        this.clientService = clientService;
        this.mappingService = mappingService;
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/clients/{id}")
    public ResponseEntity<LawClientDto> getClient(@PathVariable long id){
        Optional<LawClient> client = clientService.getClient(id);
        return ResponseEntity.of(mappingService.toClientDto(client));
    }


    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/clients")
    public ResponseEntity<List<LawClientDto>> searchClient(@RequestParam(value="name", required=false) String name)
    {
        if(name!=null){
            return ResponseEntity.ok(mappingService.toLawClientsDto(clientService.findClientLike(name)));
        }
        else{
            return ResponseEntity.ok(mappingService.toLawClientsDto(clientService.findAll()));
        }
    }

}
