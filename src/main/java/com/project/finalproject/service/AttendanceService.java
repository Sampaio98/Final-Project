package com.project.finalproject.service;

import com.project.finalproject.model.Attendance;
import com.project.finalproject.model.Professional;
import com.project.finalproject.model.User;
import com.project.finalproject.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ProfessionalService professionalService;


    public Attendance create(Attendance attendance, User user) {
        Professional professional = professionalService.findById(attendance.getProfessional().getId());

        attendance.setDateInsertion(LocalDateTime.now());
        attendance.setUser(user);
        attendance.setProfessional(professional);
        return attendance;
    }

    public Attendance insert(Attendance attendance, User user) {
        return attendanceRepository.save(create(attendance, user));
    }
}
