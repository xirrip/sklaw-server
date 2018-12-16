package ch.skunky.springdemo.service;

import ch.skunky.springdemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> getUser(String username);

    void deleteUser(String username);
    User createUser(User user);
}
