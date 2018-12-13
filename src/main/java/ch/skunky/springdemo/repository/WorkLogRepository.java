package ch.skunky.springdemo.repository;

import ch.skunky.springdemo.model.WorkLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WorkLogRepository extends CrudRepository<WorkLog, Long> {
}
