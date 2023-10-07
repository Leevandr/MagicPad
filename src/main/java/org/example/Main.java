package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.model.User;
import org.example.module.BindModule;
import org.example.service.UserAuthService;
import org.example.service.UserRegistrationService;

public class Main {

    private static Injector injector;

    public static void main(String[] args) {

        injector = Guice.createInjector(new BindModule());


        UserRegistrationService registrationService = injector.getInstance(UserRegistrationService.class);
        UserAuthService authService = injector.getInstance(UserAuthService.class);


        User user = new User(1L, "levandr123123", "levandr", "lev", "ustimenko", false);
        registrationService.registration(1L, user);


         boolean isAuthenticated = authService.Auth("levandr", "levandr123123");
         System.out.println(user.getLogin());
         System.out.println(user.getPassword());
         System.out.println("Auth successful: " + isAuthenticated);
    }
}
