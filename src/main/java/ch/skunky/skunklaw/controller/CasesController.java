package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.dto.law.LawCaseDto;
import ch.skunky.skunklaw.dto.law.service.LawDtoMappingService;
import ch.skunky.skunklaw.model.law.LawClient;
import ch.skunky.skunklaw.model.law.LawCase;
import ch.skunky.skunklaw.service.CaseService;
import ch.skunky.skunklaw.service.ClientService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CasesController {

    final private ClientService clientService;
    final private CaseService caseService;
    final private LawDtoMappingService mappingService;

    @Autowired
    public CasesController(ClientService clientService, CaseService caseService, LawDtoMappingService mappingService) {
        this.clientService = clientService;
        this.caseService = caseService;
        this.mappingService = mappingService;
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/cases")
    public ResponseEntity<List<LawCaseDto>> getAllLawCases()
    {
        List<LawCaseDto> cases =
                caseService.findAll().stream().map(
                        c -> {
                            return mappingService.toDto(c.getMainClient(), c);
                        }
                )
                .collect(Collectors.toList());

        return ResponseEntity.ok(cases);
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/cases/{id}")
    public ResponseEntity<LawCaseDto> getLawCase(@PathVariable("id") long caseId)
    {
        Optional<LawCase> lawCase = caseService.getCase(caseId);
        if(lawCase.isPresent()){
            return ResponseEntity.ok(mappingService.toDto(lawCase.get().getMainClient(), lawCase.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('write')")
    @PutMapping("/cases/{id}")
    public ResponseEntity<Object> updateLawCase(@RequestBody LawCaseDto lawCase, @PathVariable long id) {
        if(id != lawCase.getId()){
            return ResponseEntity.badRequest().build();
        }

        caseService.save(mappingService.fromDto(lawCase));
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAuthority('read')")
    @GetMapping(value="/clients/{id}/cases")
    public ResponseEntity<List<LawCaseDto>> getLawCases(@PathVariable("id") long clientId)
    {
        Optional<LawClient> client = clientService.getClient(clientId);
        if(client.isPresent()){
            return ResponseEntity.ok(mappingService.toLawCasesDto(client.get(), caseService.getCasesForClient(clientId)));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasAuthority('write')")
    @PostMapping("/clients/{id}/cases")
    public ResponseEntity<LawCaseDto> createLawCase(@PathVariable("id") long clientId, @RequestBody LawCaseDto lawCaseDto) {
        Optional<LawClient> client = clientService.getClient(clientId);

        if(client.isPresent()){
            LawCase lawCase = mappingService.fromDto(lawCaseDto);
            lawCase.setMainClient(client.get());

            if(lawCase.getCaseId()==null){
                lawCase.setCaseId(RandomStringUtils.randomAlphanumeric(10));
            }

            LawCase savedCase = caseService.save(lawCase);
            return ResponseEntity.ok(mappingService.toDto(savedCase.getMainClient(), savedCase));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
