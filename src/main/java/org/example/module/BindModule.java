package org.example.module;

import com.google.inject.AbstractModule;
import org.example.repository.CreatedTestRepository;
import org.example.repository.CreatedTestRepositoryImpl;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.service.*;

public class BindModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(UserRegistrationService.class).to(UserRegistrationServiceImpl.class);
        bind(UserAuthService.class).to(UserAuthServiceImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
        bind(CreatedTestService.class).to(CreatedTestServiceImpl.class);
        bind(CreatedTestRepository.class).to(CreatedTestRepositoryImpl.class);

    }
}
