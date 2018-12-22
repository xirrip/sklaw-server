package ch.skunky.skunklaw.service;

import ch.skunky.skunklaw.model.Client;
import ch.skunky.skunklaw.model.LawCase;
import ch.skunky.skunklaw.repository.ClientRepository;
import ch.skunky.skunklaw.repository.LawCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private LawCaseRepository lawCaseRepository;

    // make dependency visible
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, LawCaseRepository lawCaseRepository){
        this.clientRepository = clientRepository;
        this.lawCaseRepository = lawCaseRepository;
    }

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

    @Override
    public LawCase save(LawCase lawCase) {
        return lawCaseRepository.save(lawCase);
    }

}


