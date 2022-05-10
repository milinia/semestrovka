package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    public void updatePassword(Long id, String password);
//    public User findUserByEmailAndPassword(String email, String password);
    boolean existsUserByEmail(String email);
    User findUserByEmailAndAndPasswordHash(String email, String passwordHash);
    void deleteById(Long id);
    Optional<User> findUserByEmail(String email);

    @Modifying
    @Query(value = "update User u set u.passwordHash = ?2 where u.email = ?1")
    void updatePasswordByEmail(String email, String newPassword);

    @Modifying
    @Query(value = "update User u set u.passwordHash = ?2 where u.id = ?1")
    void updatePasswordById(Long id, String newPassword);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.nickname = ?2 where u.id = ?1")
    void setNicknameById(Long id, String nickname);
}
