package com.project.finalproject.controller;

import com.project.finalproject.dto.LoginDTO;
import com.project.finalproject.dto.UserDTO;
import com.project.finalproject.dto.UserInsertDTO;
import com.project.finalproject.dto.UserUpdateDTO;
import com.project.finalproject.model.User;
import com.project.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/user")
    public ResponseEntity<Void> insert(@Valid @RequestBody UserInsertDTO userInsertDTO) {
        User userSaved = service.insert(userInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(userSaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> findAllDTO() {
        List<UserDTO> users = service.findAllDTO();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = new User().fromDTO(userUpdateDTO);
        service.update(id, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> softDeleteById(@PathVariable("id") Long id) {
        service.softDeleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/user/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {
        User user = service.login(loginDTO);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/user/request")
    public ResponseEntity<Void> requestAttendance(@RequestBody UserDTO userDTO) {
        service.requestAttendance(userDTO);
        return ResponseEntity.ok().build();
    }
}
