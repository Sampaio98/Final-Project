package com.project.finalproject.dto;

import com.project.finalproject.annotation.AttendanceTypeValidation;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AttendanceTypeValidation
public class AttendanceTypeDTO {

    private String name;

    public AttendanceTypeDTO(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceTypeDTO that = (AttendanceTypeDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }
}
