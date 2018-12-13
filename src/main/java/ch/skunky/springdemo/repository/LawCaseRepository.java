package ch.skunky.springdemo.repository;

import ch.skunky.springdemo.model.LawCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LawCaseRepository extends CrudRepository<LawCase, Long> {
}
