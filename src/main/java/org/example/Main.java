package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.module.BindModule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Main {

    private static Injector injector;

    public static void main(String[] args) {

        injector = Guice.createInjector(new BindModule());


        SpringBootApplication.run(Main.class, args);
    }

}
