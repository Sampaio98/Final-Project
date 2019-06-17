package com.project.finalproject.dto;

import com.project.finalproject.model.Address;
import com.project.finalproject.model.AttendanceType;
import com.project.finalproject.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class AttendanceDTO {

    private Long id;

    private Integer situation;

    private LocalDateTime dateInsertion;

    private LocalDateTime dateModification;

    private Integer evaluation;

    private Boolean worth;

    private String description;

    private User user;

    private ProfessionalDTO professionalDTO;

    private AttendanceType attendanceType;

    private Address address;

    public AttendanceDTO(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceDTO that = (AttendanceDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(situation, that.situation) &&
                Objects.equals(dateInsertion, that.dateInsertion) &&
                Objects.equals(dateModification, that.dateModification) &&
                Objects.equals(evaluation, that.evaluation) &&
                Objects.equals(worth, that.worth) &&
                Objects.equals(description, that.description) &&
                Objects.equals(user, that.user) &&
                Objects.equals(professionalDTO, that.professionalDTO) &&
                Objects.equals(attendanceType, that.attendanceType) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, situation, dateInsertion, dateModification, evaluation, worth, description, user, professionalDTO, attendanceType, address);
    }
}
