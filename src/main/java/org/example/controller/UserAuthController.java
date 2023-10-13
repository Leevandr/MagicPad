package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/auth")


public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/{User}")

    public HttpStatus userAuth(@RequestBody User user) {
        try {
            userAuthService.Auth(user.getLogin(), user.getPassword());
            return HttpStatus.OK;

        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

    }

}
