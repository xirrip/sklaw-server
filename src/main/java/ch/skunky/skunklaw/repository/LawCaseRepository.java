package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.LawCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LawCaseRepository extends CrudRepository<LawCase, Long> {
}
