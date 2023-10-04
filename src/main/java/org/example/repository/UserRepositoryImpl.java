package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> userMap = new HashMap<>();


    @Override
    public void add(final long id , final User user) {
        userMap.put(id, user);
    }
    public Optional<User> get(final long id){
        return Optional.ofNullable(userMap.get(id));
    }
}
