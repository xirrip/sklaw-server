package ch.skunky.springdemo.repository;

import ch.skunky.springdemo.model.LawTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LawTaskRepository extends CrudRepository<LawTask, Long> {
}
