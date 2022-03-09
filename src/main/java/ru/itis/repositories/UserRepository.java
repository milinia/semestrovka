package ru.itis.repositories;

import ru.itis.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    public void updatePassword(Long id, String password);
    public User findUserByEmailAndPassword(String email, String password);
}
