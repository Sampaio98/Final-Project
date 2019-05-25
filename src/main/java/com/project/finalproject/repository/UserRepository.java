package com.project.finalproject.repository;

import com.project.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndDeletedFalse(String email);

    User findByCpfAndDeletedFalse(String cpf);

    User findByPhoneAndDeletedFalse(String phone);

    User findByUsernameAndDeletedFalse(String username);

    User findByUsernameAndPasswordAndDeletedFalse(String username, String password);
}
