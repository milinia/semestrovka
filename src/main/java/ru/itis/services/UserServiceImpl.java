package ru.itis.services;

import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }

    public void save(User user) { userRepository.save(user); }

//    public User findByEmail(String email) {
//        return DatabaseConnection.findUserByEmail(email);
//    }

    @Override
    public User findUserById(Long id) {
       Optional<User> optUser = userRepository.findById(id);
        return optUser.orElse(null);
    }

    public void updatePassword(Long id, String newPassword) {
        userRepository.updatePassword(id, newPassword);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
