package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Auth;
import ru.itis.models.User;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findAuthByCookieValue(String cookieValue);
    void deleteAuthByCookieValue(String cookie);
}
