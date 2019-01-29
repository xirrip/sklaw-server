package ch.skunky.skunklaw.service;

import ch.skunky.skunklaw.model.law.LawCase;
import ch.skunky.skunklaw.model.law.LawTask;

import java.util.List;
import java.util.Optional;

public interface CaseService {
    List<LawCase> findAll();
    List<LawCase> findCaseLike(String name);

    Optional<LawCase> getCase(long id);

    List<LawTask> findAllTasks(long caseId);

    void deleteCase(long id);

    LawCase save(LawCase lawCase);
    LawTask save(LawTask lawTask);

    List<LawCase> getCasesForClient(long clientId);
}
