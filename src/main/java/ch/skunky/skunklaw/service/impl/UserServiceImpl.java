package ch.skunky.skunklaw.service.impl;

import ch.skunky.skunklaw.model.User;
import ch.skunky.skunklaw.repository.UserRepository;
import ch.skunky.skunklaw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    // make dependency visible
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<User>();
        Iterable<User> userIterable = userRepository.findAll();
        userIterable.forEach(users::add);
        return users;
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}


