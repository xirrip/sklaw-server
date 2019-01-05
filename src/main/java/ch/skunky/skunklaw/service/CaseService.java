package ch.skunky.skunklaw.service;

import ch.skunky.skunklaw.model.LawCase;

import java.util.List;
import java.util.Optional;

public interface CaseService {
    List<LawCase> findAll();
    List<LawCase> findCaseLike(String name);

    Optional<LawCase> getCase(long id);

    void deleteCase(long id);

    LawCase save(LawCase client);

    List<LawCase> getCasesForClient(long clientId);
}
