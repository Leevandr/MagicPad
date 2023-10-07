package org.example.module;

import com.google.inject.AbstractModule;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.service.UserAuthService;
import org.example.service.UserAuthServiceImpl;
import org.example.service.UserRegistrationService;
import org.example.service.UserRegistrationServiceImpl;

public class BindModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(UserRegistrationService.class).to(UserRegistrationServiceImpl.class);
        bind(UserAuthService.class).to(UserAuthServiceImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }
}
