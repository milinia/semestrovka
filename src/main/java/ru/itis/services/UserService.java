package ru.itis.services;

import ru.itis.dtos.RegistrationDto;
import ru.itis.models.User;

public interface UserService {

    void updatePasswordByEmail(String email, String newPassword);

    void updatePasswordById(Long id, String newPassword);

    void delete(Long id);

    User findUserByEmailAndPassword(String email, String password);

    void signUp(RegistrationDto dto);

    void addUserNicknameById(Long id, String nickname);
}
