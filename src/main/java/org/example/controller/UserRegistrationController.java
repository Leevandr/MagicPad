package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PutMapping("/registration")
    public HttpStatus userRegistration(@RequestBody long id, User user) {

        try {
            userRegistrationService.registration(id, user);
            return HttpStatus.OK;

        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
