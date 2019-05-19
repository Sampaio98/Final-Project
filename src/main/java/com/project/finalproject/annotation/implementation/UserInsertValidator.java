package com.project.finalproject.annotation.implementation;

import com.project.finalproject.annotation.UserInsert;
import com.project.finalproject.dto.UserInsertDTO;
import com.project.finalproject.exception.FieldMessage;
import com.project.finalproject.model.User;
import com.project.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserInsertDTO> {

    @Autowired
    private UserRepository repository;


    @Override
    public void initialize(UserInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserInsertDTO userDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User userEmail = repository.findByEmail(userDTO.getEmail());
        User userCpf = repository.findByCpf(userDTO.getCpf());
        User userPhone = repository.findByPhone(userDTO.getPhone());
        User username = repository.findByUsername(userDTO.getUsername().toUpperCase());

        if (userEmail != null) {
            list.add(new FieldMessage("email", "Email já existente."));
        }

        if (userCpf != null) {
            list.add(new FieldMessage("cpf", "CPF já existente."));
        }

        if (userPhone != null) {
            list.add(new FieldMessage("phone", "Celular já existente."));
        }

        if (username != null) {
            list.add(new FieldMessage("username", "Usuário já existente."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
