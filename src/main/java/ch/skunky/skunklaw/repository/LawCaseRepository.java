package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.LawCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LawCaseRepository extends CrudRepository<LawCase, Long> {
    List<LawCase> findLawCasesByNameContaining(String name);

    List<LawCase> findByMainClientId(long clientId);
}
