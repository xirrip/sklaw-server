package ch.skunky.skunklaw.service.impl;

import ch.skunky.skunklaw.model.law.LawCase;
import ch.skunky.skunklaw.model.law.LawTask;
import ch.skunky.skunklaw.repository.ClientRepository;
import ch.skunky.skunklaw.repository.LawCaseRepository;
import ch.skunky.skunklaw.repository.LawTaskRepository;
import ch.skunky.skunklaw.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaseServiceImpl implements CaseService {

    final private ClientRepository clientRepository;
    final private LawCaseRepository lawCaseRepository;
    final private LawTaskRepository lawTaskRepository;

    // make dependency visible
    @Autowired
    public CaseServiceImpl(ClientRepository clientRepository, LawCaseRepository lawCaseRepository, LawTaskRepository lawTaskRepository){
        this.clientRepository = clientRepository;
        this.lawCaseRepository = lawCaseRepository;
        this.lawTaskRepository = lawTaskRepository;
    }

    @Override
    public List<LawCase> findAll() {
        List<LawCase> cases = new ArrayList<>();

        Iterable<LawCase> lawCaseIterable = lawCaseRepository.findAll();
        lawCaseIterable.forEach(cases::add);

        return cases;
    }

    @Override
    public List<LawCase> findCaseLike(String name) {
        List<LawCase> cases = new ArrayList<>();
        Iterable<LawCase> lawCaseIterable = lawCaseRepository.findLawCasesByNameContaining(name);
        lawCaseIterable.forEach(cases::add);

        return cases;
    }

    @Override
    public List<LawCase> getCasesForClient(long clientId) {
        return lawCaseRepository.findByMainClientId(clientId);
    }

    @Override
    public Optional<LawCase> getCase(long id) {
        return lawCaseRepository.findById(id);
    }

    @Override
    public List<LawTask> findAllTasks(long caseId){
        return lawTaskRepository.findByLawCaseId(caseId);
    }

    @Override
    public void deleteCase(long id) {
        lawCaseRepository.deleteById(id);
    }

    @Override
    public LawCase save(LawCase lawCase) {
        return lawCaseRepository.save(lawCase);
    }

    @Override
    public LawTask save(LawTask lawTask) {
        return lawTaskRepository.save(lawTask);
    }
}


