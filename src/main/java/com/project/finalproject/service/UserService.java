package com.project.finalproject.service;

import com.project.finalproject.dto.AttendanceDTO;
import com.project.finalproject.dto.LoginDTO;
import com.project.finalproject.dto.UserDTO;
import com.project.finalproject.dto.UserInsertDTO;
import com.project.finalproject.exception.handler.ObjectNotFoundException;
import com.project.finalproject.model.Attendance;
import com.project.finalproject.model.User;
import com.project.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
public class UserService {

    private static final String CLASS_NAME = "Usuário não encontrado.";
    private static final String USER_NOT_FOUND = "Usuário ou senha incorreto.";

    @Autowired
    private UserRepository repository;

    @Autowired
    private AttendanceService attendanceService;


    public User insert(User user) {
        return repository.save(user);
    }

    public User insert(UserInsertDTO userInsertDTO) {
        User user = new User().fromDTO(userInsertDTO);
        return insert(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(CLASS_NAME));
    }

    public List<UserDTO> findAllDTO() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(user -> new UserDTO().convertToDto(user))
                .collect(Collectors.toList());
    }

    public void softDeleteById(Long id) {
        User user = findById(id);
        user.softDelete();
    }

    public void update(Long id, User userFromFront) {
        User userFromDb = findById(id);
        userFromDb.update(userFromFront);
        repository.save(userFromDb);
    }

    public User login(LoginDTO loginDTO) {
        User user = repository.findByUsernameAndPasswordAndDeletedFalse(loginDTO.getUsername(), loginDTO.getPassword());
        if (isNull(user)) {
            throw new ObjectNotFoundException(USER_NOT_FOUND);
        } else {
            return user;
        }
    }

    public void requestAttendance(UserDTO userDTO) {
        if (nonNull(userDTO.getAttendanceDTO())) {
            User user = findById(userDTO.getId());
            AttendanceDTO attendanceDTO = userDTO.getAttendanceDTO();
            Attendance attendance = new Attendance().fromDTO(attendanceDTO);
            attendanceService.insert(attendance, user);

        } else {
            //TODO TRATAR EXCEÇÃO QUANDO NÃO HOUVER ATTENDACES
        }
    }
}
