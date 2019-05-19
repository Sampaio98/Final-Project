package com.project.finalproject.service;

import com.project.finalproject.dto.UserDTO;
import com.project.finalproject.exception.handler.ObjectNotFoundException;
import com.project.finalproject.model.User;
import com.project.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    private static final String CLASS_NAME = "Usuário";

    @Autowired
    private UserRepository repository;


    public User insert(User user) {
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(CLASS_NAME));
    }

    public List<UserDTO> findAllDTO() {
        List<User> users = repository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO().convertToDto(user);
            userDTOS.add(userDTO);
        });
        return userDTOS;
    }

    public void softDeleteById(Long id) {
        User user = findById(id);
        user.softDelete();
    }

    public void update(Long id, User userFromFront) {
        User userFromDb = findById(id);
        userFromDb.update(userFromFront);
    }
}
