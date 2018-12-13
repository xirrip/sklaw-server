package ch.skunky.springdemo.service;

import ch.skunky.springdemo.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> find(String firstName, String lastName);
}
