package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Auth;
import ru.itis.repositories.AuthRepository;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthRepository authRepository;
    @Override
    public void deleteByCookieValue(String cookie) {
        authRepository.deleteAuthByCookieValue(cookie);
    }

    @Override
    public Optional<Auth> getAuthByCookieValue(String cookie) {
       return authRepository.findAuthByCookieValue(cookie);
    }
}
