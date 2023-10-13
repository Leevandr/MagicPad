package org.example.repository;

import org.example.model.User;

import java.util.Optional;


public interface UserRepository {
    void add(final long id, final User user);

    Optional<User> get(final long id);

    Optional<User> findByLogin(final String login);

}
