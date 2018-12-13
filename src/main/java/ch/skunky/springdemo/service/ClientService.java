package ch.skunky.springdemo.service;

import ch.skunky.springdemo.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    List<Client> findClientLike(String name);

    Optional<Client> getClient(long id);

    void deleteClient(long id);

    Client save(Client client);
}
