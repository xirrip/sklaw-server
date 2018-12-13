package ch.skunky.springdemo.service;

import ch.skunky.springdemo.model.Client;
import ch.skunky.springdemo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.LastModified;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> find(String firstName, String lastName) {
        return clientRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}


