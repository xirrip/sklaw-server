package ch.skunky.skunklaw.service;

import ch.skunky.skunklaw.model.law.LawClient;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<LawClient> findAll();
    List<LawClient> findClientLike(String name);

    Optional<LawClient> getClient(long id);

    void deleteClient(long id);

    LawClient save(LawClient lawClient);
}
