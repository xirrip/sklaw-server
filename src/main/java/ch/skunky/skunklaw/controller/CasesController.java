package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.model.Client;
import ch.skunky.skunklaw.model.LawCase;
import ch.skunky.skunklaw.service.CaseService;
import ch.skunky.skunklaw.service.ClientService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CasesController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CaseService caseService;

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/cases")
    public ResponseEntity<List<LawCase>> getAllLawCases()
    {
        return ResponseEntity.ok(caseService.findAll());
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/cases/{id}")
    public ResponseEntity<LawCase> getLawCase(@PathVariable("id") long caseId)
    {

        Optional<LawCase> lawCase = caseService.getCase(caseId);
        if(lawCase.isPresent()){
            return ResponseEntity.ok(lawCase.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/clients/{id}/cases")
    public ResponseEntity<List<LawCase>> getLawCases(@PathVariable("id") long clientId)
    {
        Optional<Client> client = clientService.getClient(clientId);
        if(client.isPresent()){
            return ResponseEntity.ok(caseService.getCasesForClient(clientId));
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
            lawCase.setMainClientId(clientId);

            if(lawCase.getCaseId()==null){
                lawCase.setCaseId(RandomStringUtils.randomAlphanumeric(10));
            }

            LawCase savedCase = caseService.save(lawCase);
            return ResponseEntity.ok(savedCase);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
