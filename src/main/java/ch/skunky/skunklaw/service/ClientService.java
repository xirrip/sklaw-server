package ch.skunky.skunklaw.service;

import ch.skunky.skunklaw.model.Client;
import ch.skunky.skunklaw.model.LawCase;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    List<Client> findClientLike(String name);

    Optional<Client> getClient(long id);

    void deleteClient(long id);

    Client save(Client client);

    LawCase save(LawCase lawCase);
}
