package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController("/user")
public class UserController {


    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@RequestBody long id){
        return userRepository.get(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUserByLogin(@RequestBody String login){
        return userRepository.findByLogin(login)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }


}
