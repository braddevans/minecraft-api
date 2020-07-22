package uk.co.breadhub.mcapi.service;

import uk.co.breadhub.mcapi.model.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> find(Long id);

    Optional<User> findByUUID(String uuid);

    Iterable<User> findAll();

    void delete(Long id);

    void delete(User user);

    void deleteAll();

    long count();

}