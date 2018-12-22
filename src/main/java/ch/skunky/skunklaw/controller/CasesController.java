package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.model.Client;
import ch.skunky.skunklaw.model.LawCase;
import ch.skunky.skunklaw.service.ClientService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CasesController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/clients/{id}/cases")
    public ResponseEntity<List<LawCase>> getLawCases(@PathVariable("id") long clientId)
    {
        Optional<Client> client = clientService.getClient(clientId);
        if(client.isPresent()){
            return ResponseEntity.ok(client.get().getCases());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasAuthority('write')")
    @PostMapping("/clients/{id}/cases")
    public ResponseEntity<LawCase> createLawCase(@PathVariable("id") long clientId, @RequestBody LawCase lawCase) {
        Optional<Client> client = clientService.getClient(clientId);

        if(client.isPresent()){
            if(lawCase.getCaseId()==null){
                lawCase.setCaseId(RandomStringUtils.randomAlphanumeric(10));
            }
            String caseId = lawCase.getCaseId();

            List<Client> clients = new ArrayList<>();
            clients.add(client.get());

            LawCase savedCase = clientService.save(lawCase);
            return ResponseEntity.ok(savedCase);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
