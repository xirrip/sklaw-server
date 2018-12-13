package ch.skunky.springdemo.repository;

import ch.skunky.springdemo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * https://www.baeldung.com/spring-data-repositories
 */

@Repository
@Transactional
public interface ClientRepository extends CrudRepository<Client, Long> {

    public List<Client> findClientByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

}
