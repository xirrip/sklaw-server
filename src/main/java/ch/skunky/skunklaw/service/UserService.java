package ch.skunky.skunklaw.service;

import ch.skunky.skunklaw.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> getUser(String username);

    void deleteUser(String username);
    User createUser(User user);
}
