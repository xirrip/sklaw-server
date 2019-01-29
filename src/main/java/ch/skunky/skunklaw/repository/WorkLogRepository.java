package ch.skunky.skunklaw.repository;

import ch.skunky.skunklaw.model.law.WorkLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface WorkLogRepository extends CrudRepository<WorkLog, Long> {
    List<WorkLog> findByLawTaskId(long lawTaskId);
}
