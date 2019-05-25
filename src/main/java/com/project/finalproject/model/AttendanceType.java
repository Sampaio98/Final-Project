package com.project.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.finalproject.dto.AttendanceTypeDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ATTENDANCE_TYPE")
public class AttendanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "service_name", unique = true)
    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "date_insertion")
    private LocalDateTime dateInsertion;

    @JsonIgnore
    @OneToMany(mappedBy = "attendanceType", fetch = FetchType.LAZY)
    private Set<Attendance> attendances;

    public AttendanceType() {
        this.dateInsertion = LocalDateTime.now();
        this.attendances = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public AttendanceType fromDTO(AttendanceTypeDTO attendanceTypeDTO) {
        BeanUtils.copyProperties(attendanceTypeDTO, this);
        return this;
    }
}
