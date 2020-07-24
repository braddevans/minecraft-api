package uk.co.breadhub.mcapi.service;

import org.springframework.stereotype.Service;
import uk.co.breadhub.mcapi.model.User;

import java.util.Optional;

@Service
public interface UserService {

    User save(User user);

    Optional<User> findByUuid(String uuid);

    Optional<User> findByName(String name);

    Iterable<User> findAll();

    long count();

}