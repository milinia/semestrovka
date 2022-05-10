package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dtos.RegistrationDto;
import ru.itis.exception.NoSuchUserException;
import ru.itis.exception.UserAlreadyExistException;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void updatePasswordByEmail(String email, String newPassword){
        userRepository.updatePasswordByEmail(email, newPassword);
    }

    @Override
    public void updatePasswordById(Long id, String newPassword) {
        userRepository.updatePasswordById(id, newPassword);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findUserByEmailAndAndPasswordHash(email, password);
        if (user != null) {
            return user;
        } else
            throw new NoSuchUserException("No user with that email and password");
    }

    @Override
    public void signUp(RegistrationDto dto) {
        if (!userRepository.existsUserByEmail(dto.getEmail())){
            User user = User.builder()
                    .email(dto.getEmail())
                    .passwordHash(passwordEncoder.encode(dto.getPassword()))
                    .build();
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException("User with that email already extists!");
        }
    }

    @Override
    public void addUserNicknameById(Long id, String nickname) {
        userRepository.setNicknameById(id, nickname);
    }
}
