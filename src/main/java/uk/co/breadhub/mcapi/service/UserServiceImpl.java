package uk.co.breadhub.mcapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.breadhub.mcapi.database.UserRepository;
import uk.co.breadhub.mcapi.model.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUUID(String uuid) {
        User _user = null;
        String _uuid = uuid.replace("-", "");
        for (User user : userRepository.findAll()) {
            if (user.getUuid().contains(_uuid)) {
                _user = user;
            }
        }
        return Optional.ofNullable(_user);
    }

    @Override
    public Optional<User> findByName(String name) {
        User _user = null;
        for (User user : userRepository.findAll()) {
            if (user.getName().contains(name)) {
                _user = user;
            }
        }
        return Optional.ofNullable(_user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public long count() {
        return userRepository.count();
    }

}