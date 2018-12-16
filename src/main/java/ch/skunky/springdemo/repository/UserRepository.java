package ch.skunky.springdemo.repository;

import ch.skunky.springdemo.model.Client;
import ch.skunky.springdemo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * https://www.baeldung.com/spring-data-repositories
 * https://www.baeldung.com/spring-data-rest-relationships
 *
 * one to many mappings:
 * https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 */

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, String> {

    public Optional<User> findByUsername(String username);

}
