package ch.skunky.skunklaw.service.impl;

import ch.skunky.skunklaw.model.law.LawClient;
import ch.skunky.skunklaw.repository.ClientRepository;
import ch.skunky.skunklaw.repository.LawCaseRepository;
import ch.skunky.skunklaw.service.ClientService;
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
    public List<LawClient> findAll() {

        List<LawClient> clients = new ArrayList<LawClient>();
        Iterable<LawClient> clientIterable = clientRepository.findAllByOrderByLastNameAscFirstNameAsc();
        clientIterable.forEach(clients::add);
        return clients;
    }

    @Override
    public List<LawClient> findClientLike(String name) {
        List<LawClient> clients = new ArrayList<LawClient>();
        Iterable<LawClient> clientIterable = clientRepository.findClientByFirstNameContainingOrLastNameContaining(name, name);
        clientIterable.forEach(clients::add);

        return clients;
    }

    @Override
    public Optional<LawClient> getClient(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public LawClient save(LawClient lawClient) {
        return clientRepository.save(lawClient);
    }

}


