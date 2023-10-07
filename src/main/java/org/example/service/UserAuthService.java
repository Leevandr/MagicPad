package org.example.service;

import org.example.model.User;

public interface UserAuthService {
    boolean Auth(final String login, final String password);
}
