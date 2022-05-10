package ru.itis.services;

import ru.itis.models.Auth;

import java.util.Optional;

public interface AuthService {
    void deleteByCookieValue(String cookie);
    Optional<Auth> getAuthByCookieValue(String cookie);
}
