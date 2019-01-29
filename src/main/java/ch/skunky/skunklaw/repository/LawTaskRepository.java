package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.law.LawTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LawTaskRepository extends CrudRepository<LawTask, Long> {
    List<LawTask> findByLawCaseId(long lawCaseId);
}
