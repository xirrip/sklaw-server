package ch.skunky.springdemo.service;

import ch.skunky.springdemo.model.Client;
import ch.skunky.springdemo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.LastModified;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<Client>();
        Iterable<Client> clientIterable = clientRepository.findAll();
        clientIterable.forEach(clients::add);

        return clients;
    }

    @Override
    public List<Client> findClientLike(String name) {
        List<Client> clients = new ArrayList<Client>();
        Iterable<Client> clientIterable = clientRepository.findClientByFirstNameContainingOrLastNameContaining(name, name);
        clientIterable.forEach(clients::add);

        return clients;
    }

    @Override
    public Optional<Client> getClient(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}


