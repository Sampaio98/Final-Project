package com.project.finalproject.service;

import com.project.finalproject.exception.handler.DataIntegrityException;
import com.project.finalproject.exception.handler.ObjectNotFoundException;
import com.project.finalproject.model.AttendanceType;
import com.project.finalproject.repository.AttendanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Transactional
public class AttendanceService {

    private static final String DUPLICATED_DATA = "Tipo de serviço já cadastrado, tente outro.";
    private static final String CLASS_NAME = "Tipo de serviço";

    @Autowired
    private AttendanceTypeRepository repository;


    public AttendanceType insert(AttendanceType attendanceType) {
        AttendanceType typeName = repository.findByName(attendanceType.getName());

        if (nonNull(typeName)) {
            throw new DataIntegrityException(DUPLICATED_DATA);
        }

        return repository.save(attendanceType);
    }

    public AttendanceType findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(CLASS_NAME));
    }

    public List<AttendanceType> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
