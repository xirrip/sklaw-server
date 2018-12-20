package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.WorkLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WorkLogRepository extends CrudRepository<WorkLog, Long> {
}
