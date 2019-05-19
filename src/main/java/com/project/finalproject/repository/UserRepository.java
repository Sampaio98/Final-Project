package com.project.finalproject.repository;

import com.project.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByCpf(String cpf);

    User findByPhone(String phone);

    User findByUsername(String username);
}
