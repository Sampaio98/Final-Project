package com.project.finalproject.annotation.implementation;

import com.project.finalproject.annotation.UserUpdate;
import com.project.finalproject.dto.UserUpdateDTO;
import com.project.finalproject.exception.FieldMessage;
import com.project.finalproject.model.User;
import com.project.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdate, UserUpdateDTO> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;


    @Override
    public void initialize(UserUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserUpdateDTO userUpdateDTO, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        User userEmail = repository.findByEmail(userUpdateDTO.getEmail());
        User userPhone = repository.findByPhone(userUpdateDTO.getPhone());
        User username = repository.findByUsername(userUpdateDTO.getUsername().toUpperCase());

        if (userEmail != null && !userEmail.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        if (userPhone != null && !userPhone.getId().equals(uriId)) {
            list.add(new FieldMessage("phone", "Celular já existente"));
        }

        if (username != null && !username.getId().equals(uriId)) {
            list.add(new FieldMessage("username", "Usuário já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
