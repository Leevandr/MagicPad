package org.example.repository;

import com.google.inject.Singleton;
import org.example.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Singleton
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> userMap = new HashMap<>();


    @Override
    public void add(final long id , final User user) {
        userMap.put(id, user);
    }
    public Optional<User> get(final long id){
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        for (User user : userMap.values()) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public boolean contains(final User user){
        return userMap.containsValue(user);
    }
}
