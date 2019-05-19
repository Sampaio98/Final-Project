package com.project.finalproject.annotation.implementation;

import com.project.finalproject.annotation.AttendanceTypeValidation;
import com.project.finalproject.dto.AttendanceTypeDTO;
import com.project.finalproject.exception.FieldMessage;
import com.project.finalproject.model.AttendanceType;
import com.project.finalproject.repository.AttendanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class AttendanceTypeValidationImpl implements ConstraintValidator<AttendanceTypeValidation, AttendanceTypeDTO> {

    @Autowired
    private AttendanceTypeRepository repository;

    @Override
    public void initialize(AttendanceTypeValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(AttendanceTypeDTO attendanceTypeDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        AttendanceType name = repository.findByName(attendanceTypeDTO.getName());

        if (name != null) {
            list.add(new FieldMessage("name", "Tipo de serviço já existente."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
