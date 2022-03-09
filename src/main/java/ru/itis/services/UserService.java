package ru.itis.services;

import ru.itis.models.User;

public interface UserService {

    void save(User user);

    //User findByEmail(String email);

    User findUserById(Long id);

    void updatePassword(Long id, String newPassword);

    void delete(Long id);

    User findUserByEmailAndPassword(String email, String password);
}
