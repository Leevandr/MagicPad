package org.example.service;

import com.google.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    @Override
    public boolean Auth(final String login,final String password) {
        return userRepository.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
