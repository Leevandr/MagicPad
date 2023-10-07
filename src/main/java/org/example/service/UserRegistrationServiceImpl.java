package org.example.service;


import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserRepository;


@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    @Override
    public void registration(final long id, final User user) {
        userRepository.add(id, user);
    }
}
